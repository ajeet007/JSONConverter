package jsonconverter

class Epic {
    Long epicId
    String summary
    String description
    String entityStatus
    String entityType
    String ownerName
    String projectName
    String releaseName
    String priority
    String parentId
    String empID

    static mapping = {
        epicId column: 'EpicID'
        summary column: 'Summary'
        description column: 'Description'
        description type:'text'
        entityStatus column: 'EntityStatus'
        entityType column: 'EntityType'
        ownerName column: 'OwnerName'
        projectName column: 'ProjectName'
        releaseName column: 'ReleaseName'
        priority column: 'Priority'
        parentId column: 'ParentId'
        empID column: 'EmpID'
        version false
    }
    static constraints = {
        epicId nullable: false
        summary nullable: false
        description nullable: true
        entityStatus nullable: true
        entityType nullable: true
        ownerName nullable: true
        projectName nullable: true
        releaseName nullable:  true
        priority nullable: true
        parentId nullable: true
        empID nullable: true

    }

    static void updateParentId(jKey,tKey) {
        executeUpdate 'UPDATE Epic set parentId =:jiraKey where epicId=:tpKey', [jiraKey:jKey,tpKey:tKey]
    }
    static void updateEmpID(eKey,empKey) {
        executeUpdate 'UPDATE Epic set empID =:eID where ownerName=:empName', [eID:eKey,empName:empKey]
    }
}
