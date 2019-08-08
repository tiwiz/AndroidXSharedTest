package offline.open.repository

class StyleWrapper {

    companion object {
        private const val initialPart = """
            <!DOCTYPE html>
            <html>
                <head>
                    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab&display=swap" rel="stylesheet">
                    <style>
                        body {
                          background-color: rgb(48, 48, 48);
                          color: rgb(170, 167, 167);
                          font-family: 'Roboto Slab', serif;
                        }
                        
                        a {
                          color: rgb(255, 255, 255);
                        }
                    </style>
                </head>
                <body>
        """
        private const val lastPart = """
                </body>
            </html>
        """
    }

    fun wrapIntoStyle(html: String) = "$initialPart $html $lastPart"


}