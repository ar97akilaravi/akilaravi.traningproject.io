function validateForm(){
    var title=document.forms["editMovie"]["title"].value;
    if(title==""){
        alert("Title is required");
        return false;
    }
    var titleLength = title.length;
    if(titleLength<2||titleLength>100){
        alert("Title should have 2 to 100 characters");
        return false;
    }
    var boxoffice=document.forms["editMovie"]["gross"].value;
    if(boxoffice==""){
        alert("Boxoffice is required");
        return false;
    }
    if(isNaN(boxoffice)){
        alert("Boxoffice has to be a number");
        return false;
    }
    
    var dateOfLaunch=document.forms["editMovie"]["dateOfLaunch"].value;
    if(dateOfLaunch==""){
        alert("Date of Launch is required");
        return false;
    }
    if(!dateOfLaunch.match(/^(0[1-9]|[12][0-9]|3[01])[\-\/.](?:(0[1-9]|1[012])[\-\/.](19|20)[0-9]{2})$/)){
        alert("Incorrect data format.Expected format(dd/mm/yyyy)");
        return false;
    }
    var genre=document.forms["editMovie"]["genre"].value;
    if(genre=="0"){
        alert("Genre is required");
        return false;
    }
}