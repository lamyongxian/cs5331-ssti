<html>
    <head><title>SSTI</title></head>
    <body>
        <div id="form">
            <fieldset>
                <legend>Enter Value</legend>
                <form name="form1" action="index" method="post">
                    <input type="text" name="text" id="text"/>
                    <input type="submit" value="Submit" />
                </form>
            </fieldset>
        </div>
        <div id="display">
            <p>
                Value: ${article.text}
            </p>
        </div>
    </body>
</html>