package app

import org.springframework.web.multipart.MultipartFile

class FileController {

    def userService
    def fileService
    
    def create = {
        return [ file: new FileVersion() ]
    }
    
    def save = {
        def multipartFile = request.getFile('fileData.data')

        def file = fileService.saveNewVersion( params, multipartFile )
        
        if ( file.hasErrors() ) {
            file.currentVersion.errors = file.errors
            render(view: 'post', model: [file: file.currentVersion])
        } 
        
        else {
            flash.userMessage = "file [${file.currentVersion.name}]"
            redirect(controller: 'home')
        }
    }
    
    def extractExtension( MultipartFile file ) {
        String filename = file.getOriginalFilename()
        return filename.substring(filename.lastIndexOf( "." ) + 1 )
    }
    
    def download = {
        def fileVersion = FileVersion.get(params.id)
        response.setContentType( "application-xdownload")
        response.setHeader("Content-Disposition","attachment; filename=${fileVersion.downloadName}")
        response.getOutputStream() <<   new ByteArrayInputStream( fileVersion.fileData.data )

    }
    
    def newVersion = {
        def model = [file: new FileVersion(), fileId: params.id]
        render( view: 'create', model: model )
    }
}
