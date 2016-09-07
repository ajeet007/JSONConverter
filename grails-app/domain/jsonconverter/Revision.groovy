package jsonconverter

class Revision {

    Long revisionID
    String description
    String sourceControlID
    String assignableName
    String authorEmail
    String projectName
    String issueId

    static mapping = {
        revisionID column: 'RevisionID'
        description column: 'Description'
        description type:'text'
        sourceControlID column: 'SourceControlID'
        assignableName column: 'AssignableName'
        authorEmail column: 'AuthorEmail'
        projectName column: 'ProjectName'
        issueId column: 'IssueId'
        version false
    }
    static constraints = {
        revisionID nullable: false
        description nullable: true
        sourceControlID nullable: true
        assignableName nullable: true
        authorEmail nullable: true
        projectName nullable: true
        issueId nullable: true
    }
}
