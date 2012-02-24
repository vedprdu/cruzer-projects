package app

class File {

    static hasMany = [ versions: FileVersion ]
    SortedSet versions
    FileVersion currentVersion
    
    def newVersion( version ) {
        versions = (versions)?:new TreeSet()
        versions << currentVersion
        currentVersion = version
    }

}
