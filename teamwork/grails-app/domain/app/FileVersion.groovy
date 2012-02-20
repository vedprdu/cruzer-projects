package app

class FileVersion implements Comparable{

    static belongsTo = File
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

    public int compareTo(obj) {
        return obj.dateCreated.compareTo( dateCreated )
    }
}
