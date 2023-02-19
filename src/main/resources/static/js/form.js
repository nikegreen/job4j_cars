
    function upButtonClick(number) {
      let modalPhotoDivs = document.getElementsByName('modalPhotoDiv');
      if (number>0) {
        let modalPhotoPrev = document.getElementById('modalImg'+(number-1));
        let modalPhotoCurr = document.getElementById('modalImg'+number);
        let src = modalPhotoCurr.src;
        modalPhotoCurr.src = modalPhotoPrev.src;
        modalPhotoPrev.src = src;
        let inpCurr = document.getElementById('modalPhotoId'+number);
        let inpPrev = document.getElementById('modalPhotoId'+(number-1));
        let tmpId = inpCurr.value;
        inpCurr.value = inpPrev.value;
        inpPrev.value = tmpId;
      }
    }

    function downButtonClick(number) {
      let modalPhotoDivs = document.getElementsByName('modalPhotoDiv');
      if (number<modalPhotoDivs.length-1) {
        let modalPhotoNext = document.getElementById('modalImg'+(number+1));
        let modalPhotoCurr = document.getElementById('modalImg'+number);
        let src = modalPhotoCurr.src;
        modalPhotoCurr.src = modalPhotoNext.src;
        modalPhotoNext.src = src;
        let inpNext = document.getElementById('modalPhotoId'+(number+1));
        let inpCurr = document.getElementById('modalPhotoId'+number);
        let tmpId = inpCurr.value;
        inpCurr.value = inpNext.value;
        inpNext.value = tmpId;
      }
    }

    function delButtonClick(number) {
      let modalPhotoDivs = document.getElementsByName('modalPhotoDiv');
      let img = document.getElementById('modalImg'+number);
      let inp = document.getElementById('modalPhotoId'+number);
      let last = modalPhotoDivs.length-1;
      while (number<last) {
        number=number+1;
        let imgNext = document.getElementById('modalImg'+number);
        let inpNext = document.getElementById('modalPhotoId'+number);
        img.src = imgNext.src;
        img = imgNext;
        inp.value = inpNext.value;
        inp = inpNext;
      }
      document.getElementById('modalPhotoDiv'+last).remove();
    }

    // добавляет фото в модальном окне
    function modalPhotoAdd(imgId, src, file) {
                let photoDivs = document.getElementsByName('modalPhotoDiv');
                let modalPhotoDivSize = photoDivs.length;
                // Render thumbnail.
                let div = document.createElement('div');
                div.className = 'row mb-1';
                div.setAttribute('name', 'modalPhotoDiv');
                div.id = 'modalPhotoDiv'+modalPhotoDivSize;
                div.innerHTML = ['<div class="col-11 p-0">',
                              '<img src="', src, '" alt="" name="modalImg" id="modalImg', modalPhotoDivSize, '">',
                              '<input type="hidden" name="photos.id" id="modalPhotoId',modalPhotoDivSize,'" value="',imgId,'">',
                              '<input type="hidden" name="photos_fileName" id="modalPhotoFileName',modalPhotoDivSize,'">',
                            '</div>',
                            '<!-- Кнопки -->',
                            '<div class="col-1 d-flex flex-column justify-content-around align-items-center">',
                              '<!-- Стрелка вверх -->',
                              '<button type="button"',
                                      'class="btn btm-sm btn-outline-secondar">',
                                      '<svg xmlns="http://www.w3.org/2000/svg" width="16" ',
                                      'height="16" fill="currentColor" class="bi bi-caret-up" ',
                                      'viewBox="0 0 16 16" ',
                                      'id="modalUpButton', modalPhotoDivSize, '" ',
                                      'onclick="upButtonClick(', modalPhotoDivSize, ')">',
                                '<path d="M3.204 11h9.592L8 5.519 3.204 11zm-.753-.659 4.796-5.48a1 1 0 0 1 1.506 0l4.796 5.48c.566.647.106 1.659-.753 1.659H3.204a1 1 0 0 1-.753-1.659z" />',
                              '</svg></button>',
                              '<!-- Крестик (удалить фото) -->',
                              '<button type="button" class="btn btm-sm btn-outline-secondar">',
                                      '<svg xmlns="http://www.w3.org/2000/svg" width="16" ',
                                      'height="16" fill="currentColor" class="bi bi-x-lg" ',
                                      'viewBox="0 0 16 16" ',
                                      'onclick="delButtonClick(', modalPhotoDivSize, ')">',
                                '<path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />',
                              '</svg></button>',
                              '<!-- Стрелка вниз -->',
                              '<button type="button" class="btn btm-sm btn-outline-secondar">',
                              '<svg xmlns="http://www.w3.org/2000/svg" width="16" ',
                                      'height="16" fill="currentColor" class="bi bi-caret-down" ',
                                      'viewBox="0 0 16 16" ',
                                      'id="modalDownButton', modalPhotoDivSize, '" ',
                                      'onclick="downButtonClick(', modalPhotoDivSize, ')">',
                                '<path d="M3.204 5h9.592L8 10.481 3.204 5zm-.753.659 4.796 5.48a1 1 0 0 0 1.506 0l4.796-5.48c.566-.647.106-1.659-.753-1.659H3.204a1 1 0 0 0-.753 1.659z" />',
                              '</svg></button>',
                            '</div>'].join('');
//                document.getElementById('modalPhotoFileName'+modalPhotoDivSize).setAttribute('hidden','true');
                document.getElementById('modalPhotoDivs').insertBefore(div, null);
                document.getElementById('modalPhotoFileName'+modalPhotoDivSize).file = file;
    }

    function handleFileSelectMulti(evt) {
      let files = evt.target.files;
      //document.getElementById('modalPhotoDivs').innerHTML = "";
      for (var i = 0, f; f = files[i]; i++) {
        // Only process image files.
        if (!f.type.match('image.*')) {
          alert("Только изображения....");
        }
        var reader = new FileReader();
        // Closure to capture the file information.
        reader.onload = (function(theFile) {
          return function(e) {
            let src = e.target.result;
            modalPhotoAdd(0, src, theFile);
          };
        })(f);
        // Read in the image file as a data URL.
        reader.readAsDataURL(f);
      }
      files.length = 0;
    }

    function setFiles() {
          let photoDivs = document.getElementsByName('modalPhotoDiv');
          let modalPhotoDivSize = photoDivs.length;
          let files = document.getElementById('formFileMultiple').files;
          //files.add()
          // Создаем коллекцию файлов:
          let dt = new DataTransfer();
          for(let i = 0; i < modalPhotoDivSize; i++) {
            // Создадим простой текстовый файл:
            let data = document.getElementById('modalImg'+i).src;
            let head = data.split(',');
            if(head.length > 2) {
                alert('Error! parts > 2 :' + head.length);
            }
            // separate out the mime component
            let mimeString = head[0].split(':')[1].split(';')[0];
            let ext = 'img';
            if (mimeString === 'image/png') {
                ext = 'png';
            }
            if (mimeString === 'image/jpeg') {
                ext = 'jpg';
            }
            let file = new File([head[1]], 'tmp'+i+'.'+ext, {type: mimeString});
            dt.items.add(file);
          }
          let file_list = dt.files;
          // Вставим созданную коллекцию в реальное поле:
          document.getElementById('formFileMultiple').files = file_list;
          checkFields();
    }

    function buttonFotosClick() {
        document.getElementById('buttonPhotoModal').click();
    }

    function modalSaveButtonClick(){
      let photoDivs = document.getElementsByName('modalPhotoDiv');
      let modalPhotoDivSize = photoDivs.length;
      let carouselIndicatorsDiv = document.getElementById('carouselIndicators');
      carouselIndicatorsDiv.innerHTML = "";
      let carouselInnerDiv = document.getElementById('carousel-inner');
      carouselInnerDiv.innerHTML = "";
      if (modalPhotoDivSize == 0) {
        let buttonIndicator = document.createElement('button');
        buttonIndicator.type='button';
        buttonIndicator.setAttribute('data-bs-target', '#carouselCarIndicators1');
        buttonIndicator.setAttribute('data-bs-slide-to', '0');
        buttonIndicator.className='active';
        buttonIndicator.setAttribute('aria-current', 'true');
        buttonIndicator.setAttribute('aria-label', 'нет фотографии');
        carouselIndicatorsDiv.insertBefore(buttonIndicator, null);

        let div = document.createElement('div');
        div.className = 'carousel-item active';
        div.innerHTML = ['<img src="/images/null.png" class="d-block w-100" alt="..." name="carouselImg" id="carouselImg0"  onclick="buttonFotosClick()">',
                         '<input type="hidden" name="carouselPhotoId" id="carouselPhotoId0" value="-1">'].join('');
        carouselInnerDiv.insertBefore(div, null);
      } else {
        for (let i = 0; i < modalPhotoDivSize; i++) {
          let div = document.createElement('div');
          let buttonIndicator = document.createElement('button');
          buttonIndicator.type='button';
          buttonIndicator.setAttribute('data-bs-target', '#carouselCarIndicators1');
          buttonIndicator.setAttribute('data-bs-slide-to', ''+i);
          if (i == 0) {
            buttonIndicator.className='active';
            buttonIndicator.setAttribute('aria-current', 'true');
            div.className = 'carousel-item active';
          } else {
            div.className = 'carousel-item';
          }
          buttonIndicator.setAttribute('aria-label', 'нет фотографии');
          carouselIndicatorsDiv.insertBefore(buttonIndicator, null);

          div.innerHTML = ['<img src="',document.getElementById('modalImg'+i).src,'" class="d-block w-100" alt="..." name="carouselImg" id="carouselImg', i, '">',
          '<input type="hidden" name="carouselPhotoId" id="carouselPhotoId',i,'" value="', document.getElementById('modalPhotoId'+i).value,'">'].join('');
          carouselInnerDiv.insertBefore(div, null);
        }
      }
      setFiles();
    }

    function modalCloseButtonClick(){
        document.getElementById('modalPhotoDivs').innerHTML = "";
        let caruselImages = document.getElementsByName('carouselImg');
        let firstPhotoId = document.getElementById('carouselPhotoId0');
        for(let i = 0; i < caruselImages.length; i++) {
            if (i>0 || firstPhotoId == null || firstPhotoId.value != '-1') {
                 modalPhotoAdd(document.getElementById('carouselPhotoId'+i).value, caruselImages[i].src, caruselImages[i].file);
            }
        }
        setFiles();
    }

    function setFilterModel(selectMarcId, selectModelId, selectBodyId){
        let selMarc = document.getElementById(selectMarcId);
        let selModel = document.getElementById(selectModelId);
        let selBody = document.getElementById(selectBodyId);
        let optionList = selModel.getElementsByTagName('option');
        let marcId = selMarc.value;
        let bodyId = selBody.value;
        for (i = 0; i < optionList.length; i++) {
            let optional1 = optionList[i];
            if (optional1.value>0) {
                let marcId2 = optional1.getAttribute('marcid');
                if ((marcId == marcId2) || (marcId == 0)){
                    let bodyId2 = optional1.getAttribute('bodyid');
                    if ((bodyId == bodyId2) || (bodyId == 0)) {
                         optional1.hidden = false;
                    } else {
                         optional1.hidden = true;
                    }
                } else {
                    optional1.hidden = true;
                }
            }
        }
    }

    function setFilterMarc(selectMarcId, selectModelId, selectBodyId){
        let selMarc = document.getElementById(selectMarcId);
        let selModel = document.getElementById(selectModelId);
        let selBody = document.getElementById(selectBodyId);
        let optionList = selModel.getElementsByTagName('option');
        for (i = 0; i < optionList.length; i++) {
            let optional1 = optionList[i];
            if (optional1.selected) {
                if (optional1.getAttribute('marcid') != selMarc.value) {
                    selMarc.value = optional1.getAttribute('marcid');
                }
                if (optional1.getAttribute('bodyid') != selBody.value) {
                    selBody.value = optional1.getAttribute('bodyid');
                }
            }
        }
    }

    function setModel(selectMarcId, selectModelId, selectBodyId){
        let selMarc = document.getElementById(selectMarcId+'.id');
        let selModel = document.getElementById(selectModelId+'.id');
        let selBody = document.getElementById(selectBodyId+'.id');
        let optionList = selModel.getElementsByTagName('option');
        let marcId = selMarc.value;
        let bodyId = selBody.value;
        for (i = 0; i < optionList.length; i++) {
            let optional1 = optionList[i];
            if (optional1.value>0) {
                let marcId2 = optional1.getAttribute('marcid');
                if ((marcId == marcId2) || (marcId == 0)){
                    let bodyId2 = optional1.getAttribute('bodyid');
                    if ((bodyId == bodyId2) || (bodyId == 0)) {
                         optional1.hidden = false;
                    } else {
                         optional1.hidden = true;
                    }
                } else {
                    optional1.hidden = true;
                }
            }
        }
        document.getElementById(selectMarcId+'.name').value = selMarc.options[selMarc.selectedIndex].text;
        document.getElementById(selectBodyId+'.name').value = selBody.options[selBody.selectedIndex].text;
        checkFields();
    }

    // set fields 'marc' & 'body'
    function setMarc(selectMarcId, selectModelId, selectBodyId){
        let selMarc = document.getElementById(selectMarcId+'.id');
        let selModel = document.getElementById(selectModelId+'.id');
        let selBody = document.getElementById(selectBodyId+'.id');
        let optionList = selModel.getElementsByTagName('option');
        let optional1 = optionList[selModel.selectedIndex];
        if (optional1.selected) {
            if (optional1.getAttribute('marcid') != selMarc.value) {
                selMarc.value = optional1.getAttribute('marcid');
                document.getElementById(selectMarcId+'.name').value = selMarc.options[selMarc.selectedIndex].text;
            }
            if (optional1.getAttribute('bodyid') != selBody.value) {
                selBody.value = optional1.getAttribute('bodyid');
                document.getElementById(selectBodyId+'.name').value = selBody.options[selBody.selectedIndex].text;
            }
            document.getElementById(selectModelId+'.name').value=optional1.text;
            document.getElementById(selectModelId+'.marcId').value=optional1.getAttribute('marcid');
            document.getElementById(selectModelId+'.bodyId').value=optional1.getAttribute('bodyid');
        }
        checkFields();
    }

    function setEngine(selectEngineId){
        let selEngine = document.getElementById(selectEngineId+'.id');
        let optional1 = selEngine.options[selEngine.selectedIndex];
        if (optional1.selected) {
            document.getElementById(selectEngineId+'.name').value=optional1.text;
        }
        checkFields();
    }

    function checkFields() {
        //check price
        let selPrice = document.getElementById('price');
        let isErrPrice = selPrice.value === '';
        setupErrClass(selPrice, isErrPrice);
        //check status
        let selStatus = document.getElementById('status');
        let isErrStatus = selStatus.value < 1;
        setupErrClass(selStatus, isErrStatus);
        //check marc
        let selMarc = document.getElementById('car.marc.id');
        let isErrMarc = selMarc.value < 1;
        setupErrClass(selMarc, isErrMarc);
        //check model
        let selModel = document.getElementById('car.model.id');
        let isErrModel = selModel.value < 1;
        setupErrClass(selModel, isErrModel);
        //check body
        let selBody = document.getElementById('car.body.id');
        let isErrBody = selBody.value < 1;
        setupErrClass(selBody, isErrBody);
        //check engine
        let selEngine = document.getElementById('car.engine.id');
        let isErrEngine = selEngine.value < 1;
        setupErrClass(selEngine, isErrEngine);
        //check photos
        let isErrPhotos = document.getElementsByName('modalImg').length == 0;
        document.getElementById('saveButton').disabled = isErrPrice || isErrStatus || isErrMarc || isErrModel || isErrBody || isErrEngine || isErrPhotos;
    }

    function setupErrClass(sel, isErr) {
            let cl = sel.className;
            let clErr = ' bg-danger';
            if (cl.includes(clErr)) {
                cl = cl.replace(clErr,'');
            }
            if (isErr===true) {
                cl = cl + clErr;
            }
            if (cl !== sel.className) {
                sel.className = cl;
            }
    }
/**
 * Decode a string of base64 as text
 *
 * @param data The string of base64 encoded text
 * @returns The decoded text.
 */
//function decodeBase64(data) {
//    if (typeof atob === 'function') {
//        return atob(data);
//    } else if (typeof Buffer === 'function') {
//        return Buffer.from(data, 'base64').toString('utf-8');
//    } else {
//        throw new Error('Failed to determine the platform specific decoder');
//    }
//}