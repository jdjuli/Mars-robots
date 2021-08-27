let queryService = function (){
    let text = document.getElementById("inputText").value;
    $.post( "/solve", {input: text}, function( data ) {
        console.log(data);
        if(data.success){
            $("#outputText").text(data.output);
            $("#stat_areaCovered").text(data.stats.totalAreaCovered)
            $("#stat_robotsLost").text(data.stats.totalRobotsLost)
            $("#stat_scentsLeaved").text(data.stats.totalScentsLeaved)
        }else{
            $("#outputText").text(data.output);
        }
    });
}

let clearOutput = function (){
    $("#outputText").text("");
    $("#stat_areaCovered").text("")
    $("#stat_robotsLost").text("")
    $("#stat_scentsLeaved").text("")
}