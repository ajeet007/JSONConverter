package jsonconverter

class User {
    Integer id
    String login
    String lastName
    String email
    String firstName
    String isActive
    String isAdministrator
    String role
    Role roleID
    String empID
    String status

    static mapping = {
        login column: 'Login'
        lastName column: 'LastName'
        email column: 'Email'
        firstName column: 'FirstName'
        isActive column: 'IsActive'
        isAdministrator column: 'IsAdministrator'
        role column: 'Role'
        roleID column: 'RoleID'
        empID column: 'EmpID'
        status column: 'Status'
        version false
    }

    static constraints = {
        id nullable: false
        login nullable: true
        lastName nullable: true
        email nullable: true
        firstName nullable: true
        isActive nullable: true
        isAdministrator nullable: true
        role nullable: true
        roleID nullable: true
        empID nullable: true
        status nullable: true

    }
}
