package app

class File {

    private static final int TEN_MEGS_IN_BYTES = 1024*1024*10
    byte[] data
    String name
    String description
    int size
    String extension
    User user
    Date dateCreated
    Date lastUpdated
   
    static constraints = {
        data( nullable: false, minSize: 1, maxSize: TEN_MEGS_IN_BYTES )
        name( nullable: false, blank: false )
        description( nullable: false, blank: false )
        size( nullable: false )
        extension( nullable: false )
        user( nullable: false )
    }

}
