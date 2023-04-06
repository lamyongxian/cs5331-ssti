<html>
    <head><title>SSTI</title></head>
    <body>
        <div id="form">
        <fieldset>
            <legend>Enter Value</legend>
            <form name="form1" action="index" method="post">
                <input type="text" name="value"/>
                <input type="submit" value="Submit" />
            </form>
        </fieldset>
        </div>
        <div id="display">
            ${value}
        </div>
    </body>
</html>