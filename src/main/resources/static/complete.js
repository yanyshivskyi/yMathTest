

   //alert(mycorrect);

 $('#endtest').on('submit', function(e){
        var curanswer=1;
        var countquest=0;
        var a='';
        var b='';
        var curtype="0";
        var pointType1=0;
        var countTrueType1=0;
        var point = 0.0;
        var fullpoint=0.0;
        var koefPoint=1.0;
        for (var i=0;i<1000;i++){
            a=$(`#correct_${i}_0`).val();
            if(typeof a =='undefined') break;
            curtype=$(`#typeq_${i}`).val();
            koefPoint=$(`#point_${i}`).val();
            if(curtype=="1") {
                pointType1=0;
                countTrueType1=0;
            }
            point=0.0;
            countquest++;

            for (var j=0; j<1000; j++){
                   b=$(`#correct_${i}_${j}`).val();
                   if(typeof b=='undefined')  break;

                   switch (curtype) {
                        case "0" : {
                            if(b=="1") {
                               // alert("Перекрасить в зеленый: " + i + j); //input[name="rg-${i}"]
                            }
                            if($(`input[name="rg-${i}"]:checked`).val() == j && b=="1") {
                                point++;
                            }
                            if($(`input[name="rg-${i}"]:checked`).val() == j && b=="0") {
                             ///  alert("Перекрасить в красный" + i + j);
                            }
                            break;
                        }

                        case "1" :{
                            if(b==1) countTrueType1++;

                            if($(`#c1h-${i}-${j}`).prop('checked') && b==1) {
                               pointType1++;
                             ///  alert('Перекрасить в зеленый' +i + j);
                            }

                            if(!$(`#c1h-${i}-${j}`).prop('checked') && b==1) {
                             ///   alert('Перекрасить в синий' +i + j);
                            }

                            if($(`#c1h-${i}-${j}`).prop('checked') && b==0) {
                           ///     alert('Перекрасить в красный' +i + j);
                                countTrueType1++;
                            }
                            break;
                        }

                        case "2" :{
                            if($(`#onl-${i}`).val() == b){
                                point++;
                            ///    alert("Перекрасить в зеленый" + i + j);
                            }
                            else {
                            ///    alert("Перекрасить в красный + вывести справа b(лучше снизу)" + i + j + b);
                            }
                            break;
                        }

                   }

            }

            if(curtype==1) {
                point= pointType1/countTrueType1;
            }
            fullpoint+=point*koefPoint;

        }
        alert('point: '+fullpoint);
        $(`#flpoint`).val(fullpoint);
        
        // отключить/убрать кнопки отправки теста
    });