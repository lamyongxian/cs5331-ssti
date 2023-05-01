<html>
<head><title>SSTI</title></head>
<body>
    <div id="form">
        <div>
            ${msg}
        </div>
        <p>Powered by OWASP ESAPI.</p>
        <fieldset>
            <legend>Update Index Template Safe</legend>
            <form name="form1" action="adminsafe" method="post">
                <textarea type="text" name="value" rows="20" style="width: 400px; height: 400px">
                    ${value}
                </textarea><br />
                <input type="submit" value="Update Template Safely" />
            </form>
            <form name="form2" action="adminsafereset" method="post">
                <input type="submit" value="Reset Template"/>
            </form>
        </fieldset>
    </div>
    <div id="nav">
        Return to <a href="index">index.ftlh</a>
    </div>
</body>
</html>