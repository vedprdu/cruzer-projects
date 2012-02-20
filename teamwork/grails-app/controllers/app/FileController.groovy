package app

import org.springframework.web.multipart.MultipartFile

class FileController {

    def userService
    
    def create = {
        return [ file: new FileVersion() ]
    }

    def save = {
        
        def currentVersion = new FileVersion(params)
        currentVersion.user = userService.getAuthenticatedUser()
        MultipartFile f = request.getFile('fileData.data')
        currentVersion.size = f.getSize() / 1024
        currentVersion.extension = extractExtension(f)
        File file
        if(params.fileId) {
            file = File.get( params.fileId )
            file.versions << file.currentVersion
            file.currentVersion = currentVersion
        } else {
            file = new File( currentVersion: currentVersion )
        }

        if (file.save()) {
            flash.userMessage = "file [${currentVersion.name}]"
            redirect(controller: 'home')
        } else {
            render(view: 'post', model: [file: currentVersion])
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
