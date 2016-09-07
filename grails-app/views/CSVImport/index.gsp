<%--
  Created by IntelliJ IDEA.
  User: 60923
  Date: 22/02/2016
  Time: 12:56
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Import CSV</title>
    <g:urlMappings />
    <script type="text/javascript" src="${resource(dir: 'js',file: 'jquery-1.11.1.js')}"></script>
   %{-- <script type="text/javascript" src="${resource(dir: 'js',file: 'myscript.js')}"></script>--}%
    <g:javascript library="jquery"/>
    <script type="text/javascript">
        $(document).ready(function()
        {
            $("#getProjectData").click(function()
            {
            $.ajax
            ({
                type:'POST',
                url:"${createLink(controller: 'CSVImport', action: 'getProjectData')}",
                success:function(data)
                {
                    $('#myContainer div').html('');
                    for (var i=0;i<data.length-1;i++){
                        $('#myContainer').append('<div><table><tr>'+data[i]+'</td><td><input type="button" class="tasks"  id="'+data[i]+'" value="mapping"/></td></tr></table></div>');
                        $('#myContainer').append('<div>&nbsp;</div>');
                    }
                }
                ,error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("Ajax call error ?.");
                }
            });
        });

            $(document).on('click', '.tasks', function() {
                var projectId=$(this).attr('id');
                if (projectId != undefined) {
                $.ajax
                ({
                    type: 'POST',
                    url: "${createLink(controller: 'CSVImport', action: 'mapTasks')}",
                    data:"taskID="+projectId,
                    success: function (data) {
                        alert(projectId  +"  tasks map successfully. ");
                    }
                    , error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("Ajax call error ?.");
                    }
                });
            }
            });
        });
    </script>
</head>
<body>
<center>
%{--    <g:form action="importCSV">
        <input type="file" name="filecsv"/><br/>
        <g:actionSubmit value="import"></g:actionSubmit>
    </g:form>--}%

    <g:form action="importCSV" method="post" enctype="multipart/form-data">
        <label for="file">File:</label>
        <input type="file" name="file" id="file"/>
        <input class="save" type="submit" value="Upload"/>
    </g:form>

    %{--<g:form action="importTask" method="post" enctype="multipart/form-data">
        <label for="file"> Tasks File:</label>
        <input type="file" name="taskFile" id="taskFile"/>
        <input class="save" type="submit" value="Upload"/>
    </g:form>--}%

    %{--<g:form action="importTestCase" method="post" enctype="multipart/form-data">
        <label for="file"> Test Case File:</label>
        <input type="file" name="testcaseFile" id="testcaseFile"/>
        <input class="save" type="submit" value="Upload"/>
    </g:form>--}%
    <div>
        %{--<a href="${createLink(controller: 'CSVImport', action:'getProjectData')}"> </a>--}%
            <input type="button" name="getProjectData" id="getProjectData" value="getProject"/>
    </div>
%{--<g:uploadForm action="importCSV">
    <input type="file" name="filecsv" /><br>
    <input type="submit" value="import" />
</g:uploadForm>--}%
    <div id="myContainer"></div>
</center>
</body>
</html>