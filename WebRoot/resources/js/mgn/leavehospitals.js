function followup(admission_number){
	var URL = rootPath+"/mgn/followup/followup?admission_number="+admission_number;
	
	URL = URL.replace(/\#/g, "%23");
	URL = URL.replace(/\+/g, "%2B");
	URL = URL.replace(/\ /g, "%20");
	URL = encodeURI(URL);
	location.href =URL;//location.href
}