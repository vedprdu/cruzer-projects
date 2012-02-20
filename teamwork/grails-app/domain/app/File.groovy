package app

class File {

    static hasMany = [ versions: FileVersion ]
    SortedSet versions
    FileVersion currentVersion
}
