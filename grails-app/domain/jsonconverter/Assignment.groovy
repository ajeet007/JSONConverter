package jsonconverter

class Assignment {

    Long assignmentID
    String name
    String tpID
    String email
    User userId
    static mapping = {
        assignmentID column: 'AssignmentID'
        name column: 'Name'
        name type:'text'
        tpID column: 'tpID'
        email column: 'Email'
        userId column: 'UserId'
        version false
    }

    static constraints = {
        assignmentID nullable: false
        name nullable: true
        tpID nullable: false
        email nullable: true
        userId nullable: true
    }
}