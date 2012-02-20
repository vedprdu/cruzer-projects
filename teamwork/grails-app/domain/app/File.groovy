package app

class File {

    private static final int TEN_MEGS_IN_BYTES = 1024*1024*10
    String name
    String description
    int size
    String extension
    User user
    FileData fileData
    Date dateCreated
    Date lastUpdated
   
    static constraints = {
        name( nullable: false, blank: false )
        description( nullable: false, blank: false )
        size( nullable: false )
        extension( nullable: false )
        user( nullable: false )
        fileData( nullable: false )
    }

    def getDownloadName() {
        return "${name}.${extension}"
    }

}
