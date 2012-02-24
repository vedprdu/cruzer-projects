package app

class FileService {

    static transactional = true
    public static final Integer KB_DIVIDER = 1024;
    def userService
    
    def saveNewVersion( params, multipartFile ) {
        def version = createVersionFile( params, multipartFile )
        def file = applyNewVersion( params.fileId, version )
        file.save()
        return file;
    }
    
    def applyNewVersion( fileId, version ) {
        if( fileId ) {
            File file = File.get( fileId )
            if( file ) {
                file.newVersion( version )
                return file
            }
        }
        return new File( currentVersion:version )
    }
        
    def createVersionFile( params, multipartFile ) {
        def currentVersion = new FileVersion(params)
        currentVersion.user = userService.getAuthenticatedUser()
        currentVersion.size = multipartFile.size / KB_DIVIDER
        currentVersion.extension = extractExtension(multipartFile)
        return currentVersion
    }
    
    def extractExtension( file ) {
        String filename = file.originalFilename
        return filename.substring(filename.lastIndexOf(".") + 1)
    }

}
