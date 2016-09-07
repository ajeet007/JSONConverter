package jsonconverter

class RevisionFile {
    Long revisionFileID
    String fileName
    Revision revisionID
    String revisionSourceControlId
    String revisionDescription
    String issueId

    static mapping = {
        revisionFileID column: 'revisionFileID'
        fileName column: 'FileName'
        revisionID column: 'RevisionID'
        revisionSourceControlId column: 'RevisionSourceControlId'
        revisionDescription column: 'RevisionDescription'
        revisionDescription type:'text'
        issueId column: 'IssueId'
        version false
    }
    static constraints = {
        revisionFileID nullable: false
        fileName nullable: true
        revisionID nullable: true
        revisionSourceControlId nullable: true
        revisionDescription nullable: true
        issueId nullable: true
    }
}
