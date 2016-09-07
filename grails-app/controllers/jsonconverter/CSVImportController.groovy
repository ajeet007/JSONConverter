package jsonconverter

import grails.converters.JSON

import javax.swing.Spring

class CSVImportController {
    def index = {
    }
    def importCSV = {

        def fileName = request.getFile('file');
        String originalFile = fileName.getOriginalFilename();
        if (originalFile.toLowerCase().startsWith("story")) {
            String story_id = 0;
            String summary = "";
            String description = "";
            String timeSpent = "";
            String entityStatus = "";
            String entityType = "";
            String ownerName = "";
            String priority = "";
            String projectName = "";
            String release = "";
            String sprintName = "";
            String sprintStartDate = "";
            String sprintEndDate = "";
            String featureID = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    story_id = tokens[0];
                    summary = tokens[1];
                    description = tokens[2];
                    //timeSpent = tokens[14];
                    entityStatus = tokens[3];
                    entityType = tokens[4];
                    featureID = tokens[5];
                    ownerName = tokens[6];
                    priority = tokens[7];
                    projectName = tokens[8];
                    release = tokens[9];
                    sprintName = tokens[12];
                    sprintStartDate = tokens[10];
                    sprintEndDate = tokens[11];
                    println(story_id);
                        def story = new Story(storyId: Long.parseLong(story_id), summary: summary, description: description,
                            timeSpent: timeSpent, entityStatus: entityStatus, entityType: entityType,
                            ownerName: ownerName, priority: priority, projectName: projectName,featureID: featureID, releaseName: release, sprintName: sprintName,
                            sprintStartDate: sprintStartDate, sprintEndDate: sprintEndDate).save(failOnError: true);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (originalFile.toLowerCase().startsWith("tasks")) {
            String tasks_id = "";
            String summary = "";
            String description = "";
            String entityStatus = "";
            String entityType = "";
            String ownerName = "";
            String story_id = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    tasks_id = tokens[0];
                    summary = tokens[1];
                    description = tokens[2];
                    entityStatus = tokens[3];
                    entityType = tokens[4];
                    ownerName = tokens[5];
                    story_id = tokens[7];
                    if (story_id != "") {
                        def storyid = Story.findByStoryId(Long.parseLong(story_id));
                        def tasks = new Tasks(taskId: Long.parseLong(tasks_id), summary: summary, description: description,
                                entityStatus: entityStatus, entityType: entityType,
                                ownerName: ownerName, storyId: storyid).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (originalFile.toLowerCase().startsWith("testcase")) {
            String testCase_id = "";
            String summary = "";
            String description = "";
            String entityType = "";
            String ownerName = "";
            String story_id = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    testCase_id = tokens[0];
                    summary = tokens[1];
                    description = tokens[2];
                    entityType = tokens[3];
                    ownerName = tokens[4];
                    story_id = tokens[5];
                    println("story:>>>>>" + story_id);
                    if (story_id != "") {
                        def storyid = Story.findByStoryId(Long.parseLong(story_id));
                        def testCase = new TestCase(testCaseId: Long.parseLong(testCase_id), summary: summary, description: description,
                                entityType: entityType, ownerName: ownerName, storyId: storyid).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (originalFile.toLowerCase().startsWith("epic")) {
            String epic_id = "";
            String summary = "";
            String description = "";
            String entityType = "";
            String entityStatus = "";
            String ownerName = "";
            String priority = "";
            String projectName = "";
            String release = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    epic_id = tokens[0];
                    summary = tokens[1];
                    description = tokens[2];
                    entityStatus = tokens[3];
                    entityType = tokens[4];
                    ownerName = tokens[5];
                    priority = tokens[6];
                    projectName = tokens[7];
                    release = tokens[8];
                    if (epic_id != "") {
                        def epic = new Epic(epicId: Long.parseLong(epic_id), summary: summary, description: description,
                                entityType: entityType,entityStatus:entityStatus, ownerName: ownerName, priority: priority,projectName:projectName,releaseName: release).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (originalFile.toLowerCase().startsWith("bug")) {
            String bug_id = "";
            def story_id = "";
            String summary = "";
            String description = "";
            String entityType = "";
            String entityStatus = "";
            String ownerName = "";
            String priority = "";
            String projectName = "";
            String release = "";
            String severity = "";
            String sprintName = "";
            String build = "";

            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    bug_id = tokens[0];
                    summary = tokens[1];
                    description = tokens[2];
                    build = tokens[3];
                    entityStatus = tokens[4];
                    entityType = tokens[5];
                    ownerName = tokens[6];
                    priority = tokens[7];
                    projectName = tokens[8];
                    release = tokens[9];
                    severity = tokens[10];
                    sprintName = tokens[11];
                    story_id = tokens[12];
                    if (bug_id != "") {
                        if(story_id != "") {
                            story_id= Story.findByStoryId(Long.parseLong((story_id).toString()));
                        }
                        def bug = new Bug(bugID: Long.parseLong(bug_id), summary: summary, description: description,sprintName: sprintName,severity: severity,build: build,
                                entityType: entityType,entityState: entityStatus, ownerName: ownerName, priority: priority,projectName:projectName,releaseName: release,storyId: story_id).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (originalFile.toLowerCase().startsWith("assign")) {

            String assignment_id = "";
            String name = "";
            String email = "";
            String tpID = "";
            User userId = null;

            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    assignment_id = tokens[0];
                    name = tokens[1];
                    tpID = tokens[2];
                    email = tokens[4];
                    if (assignment_id != "") {
                        userId= User.findByEmail(email);
                        def assignment = new Assignment(assignmentID: Long.parseLong(assignment_id), name: name, email: email,tpID: tpID,userId: userId).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (originalFile.toLowerCase().startsWith("revision")) {
            String revision_id = "";
            String description = "";
            String sourceControlID = "";
            String assignableName = "";
            String authorEmail = "";
            String projectName = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    revision_id = tokens[0];
                    description = tokens[1];
                    sourceControlID = tokens[2];
                    assignableName = tokens[3];
                    authorEmail = tokens[5];
                    projectName = tokens[6];
                    if (revision_id != "") {
                        String issue_id = getIssueId(description);
                        def revision = new Revision(revisionID: Long.parseLong(revision_id), description: description, sourceControlID: sourceControlID,
                                assignableName: assignableName, authorEmail: authorEmail, projectName: projectName, issueId: issue_id).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (originalFile.toLowerCase().startsWith("history")) {
            String fileRevision_id = "";
            String svnFileName ="";
            String revision_id = "";
            String revisionSourceControlID = "";
            String revisionDescription = "";
            try {
                request.getFile('file').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                    fileRevision_id = tokens[0];
                    svnFileName = tokens[1];
                    revision_id = tokens[2];
                    revisionSourceControlID = tokens[3];
                    revisionDescription = tokens[4];
                    if (fileRevision_id != "") {
                        def revision = Revision.findByRevisionID(Long.parseLong(revision_id));
                        String issue_id = getIssueId(revisionDescription);
                        def fileRevision = new RevisionFile(revisionFileID: Long.parseLong(fileRevision_id), fileName: "https://svn01.hit.com.hk/svn/repos"+svnFileName, revisionID: revision,
                                revisionSourceControlId: revisionSourceControlID, revisionDescription: revisionDescription, issueId: issue_id).save(failOnError: true);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        redirect action: 'index'
    }

    /*  def importTask={
          String tasks_id = "";
          String summary = "";
          String description = "";
          String entityStatus = "";
          String entityType = "";
          String ownerName = "";
          String story_id = "";
          try {
              request.getFile('taskFile').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                  tasks_id = tokens[0];
                  summary = tokens[1];
                  description = tokens[2];
                  entityStatus = tokens[3];
                  entityType = tokens[4];
                  ownerName = tokens[5];
                  story_id = tokens[6];
                  if (story_id != "") {
                      def storyid = Story.findByStoryId(Long.parseLong(story_id));
                      def tasks = new Tasks(taskId: Long.parseLong(tasks_id), summary: summary, description: description,
                              entityStatus: entityStatus, entityType: entityType,
                              ownerName: ownerName, storyId: storyid).save(failOnError: true);
                  }
              }
          }
          catch (Exception e) {
              e.printStackTrace();
          }
          redirect action: 'index'
      }*/

    /* def importTestCase = {
         String testCase_id = "";
         String summary = "";
         String description = "";
         String entityType = "";
         String ownerName = "";
         String story_id = "";
         try {
             request.getFile('testcaseFile').inputStream.toCsvReader(['skipLines': 1]).eachLine { tokens ->
                 testCase_id = tokens[0];
                 summary = tokens[1];
                 description = tokens[2];
                 entityType = tokens[3];
                 ownerName = tokens[4];
                 story_id = tokens[5];
                 println("story:>>>>>"+story_id);
                 if (story_id != "") {
                     def storyid = Story.findByStoryId(Long.parseLong(story_id));
                     def testCase = new TestCase(testCaseId: Long.parseLong(testCase_id), summary: summary, description: description,
                             entityType: entityType, ownerName: ownerName, storyId: storyid).save(failOnError: true);
                 }
             }
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         redirect action: 'index'
     }*/


    def getProjectData = {
        def project = Story.executeQuery("select distinct a.projectName from Story a");
        render project as JSON;
       // redirect action: 'index'
    }
    def mapTasks={
        def statusMap=[:];
        try {
            String projectName=params?.taskID;
           // def story = Story.findAllByProjectName(projectName);
            def story = Story.findAllByProjectName("TIPS");
            int i = 20;
            story.each { s ->
                // def tasks  =Tasks.findAllByStoryId(Story.findByStoryId(s?.storyId));
                //println(">>>>>>>>>>>>>>>>>" + Story.findByStoryId(s?.storyId).storyId);
                 //Tasks.updateParentId("TIP-"+i,Story.findByStoryId(s?.storyId));
                TestCase.updateParentId("TIP-" + i, Story.findByStoryId(s?.storyId));
                i = i + 1;
            }
            statusMap.put("status","success");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        render statusMap as JSON;
    }

    def mapStory={
        def statusMap=[:];
        try {
           // String projectName=params?.taskID;
               def story = Story.findAllByProjectName("Unity");
             //  def story = Story.findAllByProjectNameAndStoryIdGreaterThan("nITDS",8195);
            //int i = 19;
            int i = 12;
            story.each { s ->
                // def tasks  =Tasks.findAllByStoryId(Story.findByStoryId(s?.storyId));
                 Tasks.updateParentId("UNI-"+i,Story.findByStoryId(s?.storyId));
                 TestCase.updateParentId("UNI-" + i, Story.findByStoryId(s?.storyId));
                i = i + 1;
                println(">>>>"+Story.findByStoryId(s?.storyId));
            }
            statusMap.put("status","success");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        render statusMap as JSON;
    }

    def mapJiraEpic={
        def statusMap=[:];
        try {
            def epic = Epic.findAllByProjectName("Unity");
            int i = 1;
            epic.each { s ->
                Epic.updateParentId("UNI-"+i, Epic.findByEpicId(s?.epicId).epicId);   /*********To update epicId in Epic table ****/
                i = i + 1;
            }
            statusMap.put("status","success");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        render statusMap as JSON;
    }

    def mapEmpId={
        try {
           // def story = Story.findAll();
          //  def story=Tasks.findAll();
            def story=TestCase.findAll();
           // def story=Epic.findAll();
           // def story=Bug.findAll();
            story.each { s ->
                def user=User.findByLogin(s?.ownerName);
                if(user!=null){
                   // Story.updateEmpID(user?.empID,s?.ownerName);
                    println(user?.empID);
                  //  Tasks.updateEmpID(user?.empID,s?.ownerName);
                    TestCase.updateEmpID(user?.empID,s?.ownerName);
                   // Epic.updateEmpID(user?.empID,s?.ownerName);
                   // Bug.updateEmpID(user?.empID,s?.ownerName);
                }
                else{
                    println(s?.ownerName);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    def mapEpic={
        try {
            def epic=Epic.findAll();
            epic.each { e ->
                def story = Story.findByFeatureID(Long.toString(e?.epicId));
                if(story?.featureID != null && e.parentId != null){
                    println(Long.toString(e?.epicId)+">>>>>>>>"+story?.featureID+">>>>>>>>>>>>>>>>>>"+e.parentId);
                    Story.updateParentId(e.parentId,story?.featureID);
                }
                else{
                    println(e?.ownerName+"   story Id   "+ e?.epicId);
                }


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    def mapBug={
        try {
            def bug=Bug.findAll();
            bug.each { e ->
                if(e.storyId !=null){
                    def tasks = Tasks.findByStoryId((e.storyId));
                    if(tasks?.storyId == e?.storyId && tasks?.parentId!=null){
                        Bug.updateParentId(tasks?.parentId,e?.storyId)
                    }
                }
                else{
                    println(e?.ownerName+"   story Id   "+ e?.storyId);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    def assignment={
        /********tasks assignments *******/
/*def tasks = Tasks.findAll();
        tasks.each{s ->
            String taskId=s?.taskId;
            def assignment_id= Assignment.findByTpID(taskId);
            if(assignment_id?.tpID!=null && assignment_id?.email!=null){
               Tasks.updateAssignment(assignment_id?.email,assignment_id?.tpID);
            }
        }*/
        /********Bugs assignments *******/

        /*def bugs = Bug.findAll();
        bugs.each{s ->
            String bugId=s?.bugID;
            def assignment_id= Assignment.findByTpID(bugId);
            if(assignment_id?.tpID!=null && assignment_id?.email!=null){
               Bug.updateAssignment(assignment_id?.email,assignment_id?.tpID);
            }
        }*/
        /********Story assignments *******/
        def story = Story.findAll();
        story.each{s ->
            String storyId=s?.storyId;
            def assignment_id= Assignment.findByTpID(storyId);
            if(assignment_id?.tpID!=null && assignment_id?.email!=null){
                Story.updateAssignment(assignment_id?.email,assignment_id?.tpID);
            }
        }
    }

    def getIssueId(String desc) {
        StringTokenizer st = new StringTokenizer(desc);
        StringBuilder issueList = new StringBuilder();
        while (st.hasMoreElements()) {
            String token=st.nextElement();
            if(token.startsWith('#')){
                String issue_id = token.replace("#","");
                println(issue_id);
                if(issueList.length()>0){
                    issueList.append(",");
                }
                issueList.append(issue_id);
            }
        }
        return issueList.toString();
    }
}
