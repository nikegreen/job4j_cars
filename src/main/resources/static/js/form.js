    function upButtonClick(number) {
      let modalPhotoDivs = document.getElementsByName('modalPhotoDiv');
      if (number>0) {
        let modalPhotoPrev = document.getElementById('modalImg'+(number-1));
        let modalPhotoCurr = document.getElementById('modalImg'+number);
        let src = modalPhotoCurr.src;
        modalPhotoCurr.src = modalPhotoPrev.src;
        modalPhotoPrev.src = src;
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
      }
    }

    function delButtonClick(number) {
      let modalPhotoDivs = document.getElementsByName('modalPhotoDiv');
      let img = document.getElementById('modalImg'+number);
      let last = modalPhotoDivs.length-1;
      while (number<last) {
        number=number+1;
        let imgNext = document.getElementById('modalImg'+number);
        img.src = imgNext.src;
        img = imgNext;
      }
      document.getElementById('modalPhotoDiv'+last).remove();
    }

    function modalPhotoAdd(src) {
                let photoDivs = document.getElementsByName('modalPhotoDiv');
                let modalPhotoDivSize = photoDivs.length;
                // Render thumbnail.
                let div = document.createElement('div');
                div.className = 'row mb-1';
                div.setAttribute('name', 'modalPhotoDiv');
                div.id = 'modalPhotoDiv'+modalPhotoDivSize;
                div.innerHTML = ['<div class="col-11 p-0">',
                              '<img src="', src, '" alt="" name="modalImg" id="modalImg', modalPhotoDivSize, '">',
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
                document.getElementById('modalPhotoDivs').insertBefore(div, null);
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
            modalPhotoAdd(src);
          };
        })(f);
        // Read in the image file as a data URL.
        reader.readAsDataURL(f);
      }
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
        div.innerHTML = ['<img src="/images/null.png" class="d-block w-100" alt="..." name="carouselImg" id="carouselImg0">'].join('');
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

          div.innerHTML = ['<img src="',document.getElementById('modalImg'+i).src,'" class="d-block w-100" alt="..." name="carouselImg" id="carouselImg', i, '">'].join('');
          carouselInnerDiv.insertBefore(div, null);
        }
      }
    }

    function modalCloseButtonClick(){
          alert('close button click!');
        document.getElementById('modalPhotoDivs').innerHTML = "";
        let caruselImages = document.getElementsByName('carouselImg');
        for(let i = 0; i < caruselImages.length; i++) {
            modalPhotoAdd(caruselImages[i].src);
        }
    }