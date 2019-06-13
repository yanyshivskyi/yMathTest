

   //alert(mycorrect);

 $('#endtest').on('submit', function(e){
        var curanswer=1;
        var countquest=0;
        var a='';
        var b='';
        var curtype=0;
        var point = 0;
        for (var i=0;i<1000;i++){
            a=$(`#correct_${i}_0`).val();
            if(typeof a =='undefined') break;
            curtype=$(`#typeq_${i}`).val();
            countquest++;

            for (var j=0; j<1000; j++){
                   b=$(`#correct_${i}_${j}`).val();
                   if(typeof b=='undefined')  break;

                   if(curtype=="0" && $(`input[name="rg-${i}"]:checked`).val() == j && b=="1") point++;
                   if(curtype=="1" && $(`#c1h-${i}-${j}`).prop('checked')) {
                         alert('hhh');
                   }
                   if(curtype=="2" && $(`#onl-${i}`).val() == b) point++;
                  // alert('iscorrect-'+ b);
            }

        }
        alert('point'+point);
        alert(countquest);


    });