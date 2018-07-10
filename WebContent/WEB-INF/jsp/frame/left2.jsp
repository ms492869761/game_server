<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>

<head>
	<title>Destroydrop &raquo; Javascripts &raquo; Tree</title>

	<link rel="StyleSheet" href="dtree.css" type="text/css" />
	<script type="text/javascript" src="css/dtree/dtree.js"></script>

</head>

<body>
<div class="dtree">

	<p><a href="javascript: d.openAll();">全部展开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>

	<script type="text/javascript">

		d = new dTree('d');

		d.add(0,-1,'My example tree');
		d.add(1,0,'Node 1','css/dtree/example01.html',"title","mainFrame","css/dtree/img/imgfolder.gif");
		d.add(2,0,'Node 2','css/dtree/example01.html',"title","mainFrame");
		d.add(3,1,'Node 1.1','css/dtree/example01.html',"title","mainFrame");
		d.add(4,0,'Node 3','css/dtree/example01.html',"title","mainFrame");
		d.add(5,3,'Node 1.1.1','css/dtree/example01.html',"title","mainFrame");
		d.add(6,5,'Node 1.1.1.1','css/dtree/example01.html',"title","mainFrame");
		d.add(7,0,'Node 4','css/dtree/example01.html',"title","mainFrame");
		d.add(8,1,'Node 1.2','css/dtree/example01.html',"title","mainFrame");
		d.add(9,0,'My Pictures','css/dtree/example01.html',"title","mainFrame");
		d.add(10,9,'The trip to Iceland','css/dtree/example01.html',"title","mainFrame");
		d.add(11,9,'Mom\'s birthday','css/dtree/example01.html',"title","mainFrame");
		d.add(12,0,'Recycle Bin','css/dtree/example01.html',"title","mainFrame");

		document.write(d);

	</script>

</div>

<p><a href="mailto&#58;drop&#64;destroydrop&#46;com">&copy;2002-2003 Geir Landr&ouml;</a></p>

</body>

</html>