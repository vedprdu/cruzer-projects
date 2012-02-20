package app

class FileData {

    private static final int TEN_MEG_IN_BYTES = 1024*1024*10
    static belongsTo = [file:File]
    byte[] data
    
    static constraints = {
        data( nullable: false, minSize: 1, maxSize: TEN_MEG_IN_BYTES )
    }

}
