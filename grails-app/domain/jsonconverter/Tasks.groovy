package jsonconverter

class Tasks {
    Story storyId
    Long taskId
    String summary
    String description
    String entityStatus
    String entityType
    String ownerName
    String parentId
    String empID
    String assignTo
    static mapping = {
        storyId column: 'StoryId'
        taskId column: 'TaskId'
        summary column: 'Summary'
        description column: 'Description'
        description type: 'text'
        entityStatus column: 'EntityStatus'
        entityType column: 'EntityType'
        ownerName column: 'Owner'
        parentId column: 'ParentId_JIRA'
        empID column: 'EmpID'
        assignTo column: 'assignTo'
        version false
    }
    static constraints = {
        storyId nullable: false
        taskId nullable: false
        summary nullable: false
        description nullable: true
        entityStatus nullable: true
        entityType nullable: true
        ownerName nullable: true
        parentId nullable: true
        empID nullable: true
        assignTo nullable: true
    }

    static void updateParentId(jKey,tKey) {
        executeUpdate 'UPDATE Tasks set parentId =:jiraKey where storyId=:tpKey', [jiraKey:jKey,tpKey:tKey]
    }
    static void updateEmpID(eKey,empKey) {
        executeUpdate 'UPDATE Tasks set empID =:eID where ownerName=:empName', [eID:eKey,empName:empKey]
    }
    static void updateAssignment(jKey,tKey){
        executeUpdate 'UPDATE Tasks set assignTo =:jiraKey where taskId=:tpKey', [jiraKey:jKey,tpKey:Long.parseLong(tKey)]
    }
}
