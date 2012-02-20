package app

import org.springframework.web.multipart.MultipartFile

class FileController {

    def userService
    
    def create = {
        return [ file: new File() ]
    }
    def save = {
        def file = new File( params )
        file.user = userService.getAuthenticatedUser()
        MultipartFile f = request.getFile( 'data' )
        file.size = f.getSize() / 1024
        file.extension = extractExtension( f )
        if(file.save()) {
            flash.userMessage = "File [${file.name}] has been uploaded."
            redirect(controller: 'home')
        } 
        else {
            render(view: 'create', model: [file: file])
        }
    }
    def extractExtension( MultipartFile file ) {
        String filename = file.getOriginalFilename()
        return filename.substring(filename.lastIndexOf( "." ) + 1 )
    }
    def download = {
    }


}
