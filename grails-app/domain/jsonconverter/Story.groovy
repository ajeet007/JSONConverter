package jsonconverter

class Story {
    Long storyId
    String summary
    String description
    String timeSpent
    String entityStatus
    String entityType
    String ownerName
    String priority
    String projectName
    String releaseName
    String sprintName
    String sprintStartDate
    String sprintEndDate
    String empID
    String epicId
    String featureID
    String assignTo

    static mapping = {
       //  id column: 'StoryId'
        storyId column: 'StoryId'
        summary column: 'Summary'
        description column: 'Description'
        description type:'text'
        timeSpent column: 'TimeSpent'
        entityStatus column: 'EntityStatus'
        entityType column: 'EntityType'
        ownerName column: 'OwnerName'
        priority column: 'Priority'
        projectName column: 'ProjectName'
        releaseName column: 'ReleaseName'
        sprintName column: 'Sprint'
        sprintStartDate column: 'SprintStartDate'
        sprintEndDate column: 'SprintEndDate'
        empID column: 'EmpID'
        epicId column: 'EpicID'
        featureID column: 'FeatureID'
        assignTo column: 'assignTo'
        version false
    }
    static constraints = {
        storyId nullable: false
        summary nullable: false
        description nullable: true
        timeSpent nullable: true
        entityStatus nullable: true
        entityType nullable: true
        ownerName nullable: true
        priority nullable: true
        projectName nullable: true
        releaseName nullable: true
        sprintName nullable: true
        sprintStartDate nullable: true
        sprintEndDate nullable: true
        empID nullable: true
        epicId nullable: true
        featureID nullable: true
        assignTo nullable: true

    }
    static void updateEmpID(eKey,empKey) {
        executeUpdate 'UPDATE Story set empID =:eID where ownerName=:empName', [eID:eKey,empName:empKey]
    }
    static void updateParentId(jKey,tKey) {
        executeUpdate 'UPDATE Story set epicId =:jiraKey where featureID=:tpKey', [jiraKey:jKey,tpKey:tKey]
    }
    static void updateAssignment(jKey,tKey){
        executeUpdate 'UPDATE Story set assignTo =:jiraKey where storyId=:tpKey', [jiraKey:jKey,tpKey:Long.parseLong(tKey)]
    }

}
