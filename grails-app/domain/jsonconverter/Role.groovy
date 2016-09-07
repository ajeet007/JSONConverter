package jsonconverter

class Role {
    Integer id
    String name
    static  mapping = {
        name column: 'Name'
        version false
    }
    static constraints = {
        id nullable: false
        name nullable: true
    }
}
