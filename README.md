# PdfFactory

Spring Boot microservice with RESTful webapp and service endpoints that create PDF from HTML, 
optionally styling with CSS and templating with JSON using Flying Saucer, PDF Box and Jackson libraries

Author: Jaber Kaal
Date: 10/14/2017
Location: CA

Purpose
Even though there are many other similar projects available, the idea behind this project is to allow anyone to deploy it on existing Java based infrastructure within a public or private cloud environment.
The service allows the users to quickly build HTML page with custom styling, try it out from the web console and then simply insert variables in the HTML body which will be replaced by JSON object or JSON array provided as a separate argument to the service.
If a JSON array is provided with multiple JSON objects, then individual PDF outputs are merged together into a single PDF file and returned as such (try webconsole at {PROTOCOL}://{HOST:PORT}/html-pdf-service/).


This project uses:

Flying Saucer Pdf library to convert html and css documents (strings) into PDF.
Jackson to handle html templating with Json data.
Apache PDF Box to merge multiple pdf files into one.
Bootstrap to style web page.
JQuery to test and control page behaviour.
A few other pieces of javascript code.
