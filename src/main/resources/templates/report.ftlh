<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog report</title>
</head>
<body>
<h1>Catalog report for the catalog '${name}':</h1>
<h2>Path: ${path}</h2>
<#list docs as doc>
    <li>${doc.id} - '${doc.name}' (${doc.location})</li>
</#list>
</body>
</html>