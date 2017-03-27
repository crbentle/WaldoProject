var photos_json;
$(function() {

    $("#image").on("error", function(err) {
        $('.image-wrapper').hide();
        $("#image-loading").hide();
        return false;
    }).on("load",
        function() {
            $("#image-loading").hide();
            $(".image-wrapper").show();
        });


    // Code used for local testing
    // var photos_json = $.parseJSON('{"photos":[{"key":"0003b8d6-d2d8-4436-a398-eab8d696f0f9.68cccdd4-e431-457d-8812-99ab561bf867.jpg","size":6306109,"lastModified":1471008103000,"error":null,"exifData":{"Exposure Sequence Number":"102","Exif Version":"2.30","Compression Type":"Baseline","Number of Components":"3","CFA Pattern":"[Red,Green][Green,Blue]","Component 2":"Cb component: Quantization table 1, Sampling factors 1 horiz/1 vert","Focal Length":"78 mm","Component 1":"Y component: Quantization table 0, Sampling factors 2 horiz/1 vert","Date/Time Original":"2016:01:28 16:50:05","Flash Info":"[51 values]","Component 3":"Cr component: Quantization table 1, Sampling factors 1 horiz/1 vert","Flash Exposure Compensation":"0 EV","F-Number":"f/4.0","Sensitivity Type":"Recommended Exposure Index","Lens Type":"AF, D, G, VR","File Source":"Digital Still Camera (DSC)","Auto Flash Mode":"Optional,TTL-BL","ISO Info":"96 1 12 0 0 0 96 1 12 0 0 0 0 0","Image Boundary":"0 0 6016 4016","Make":"NIKON CORPORATION","Orientation":"Top, left side (Horizontal / normal)","Metering Mode":"Multi-segment","Contrast":"None","Interoperability Index":"Recommended Exif Interoperability Rules (ExifR98)","AF Info 2":"[30 values]","Thumbnail Offset":"19864 bytes","Crop High Speed":"11 6032 4032 6032 4032 0 0","Gain Control":"Low gain up","Flash Used":"External Flash","Exif Image Height":"4016 pixels","Lens":"24-120mm f/4.0","Exposure Tuning":"0 EV","Y Resolution":"300 dots per inch","Shot Info":"[15420 values]","White Balance":"AUTO1","Sensing Method":"One-chip color area sensor","Active D-Lighting":"Off","World Time":"152 254 0 2","Lens Stops":"5 EV","Thumbnail Length":"9248 bytes","Program Shift":"0 EV","Noise Reduction":"OFF","Exposure Difference":"-2.08 EV","Date/Time":"2016:01:28 16:50:05","White Balance Fine":"0 0","Exif Image Width":"6016 pixels","Image Height":"4016 pixels","White Balance RB Coefficients":"516/256 321/256 256/256 256/256","Model":"NIKON D750","Auto Flash Compensation":"0 EV","Sub-Sec Time":"50","White Balance Mode":"Auto white balance","Compression":"JPEG (old-style)","Number of Tables":"4 Huffman tables","Components Configuration":"YCbCr","YCbCr Positioning":"Datum point","Sub-Sec Time Digitized":"50","Camera Serial Number":"3059982","X Resolution":"300 dots per inch","Interoperability Version":"1.00","Focal Length 35":"78 mm","VR Info":"48 49 48 48 2 2 0 0","Quality & File Format":"NORMAL","Color Balance":"[1224 values]","Exposure Program":"Manual control","Digital Zoom Ratio":"1","File Info":"[172 values]","GPS Version ID":"2.300","Retouch History":"0 0 0 0 0 0 0 0 0 0","AF Type":"AF-A","Exposure Time":"1/125 sec","ISO Speed Ratings":"800","AE Bracket Compensation":"0","Multi Exposure":"48 49 48 48 0 0 0 0 0 0 0 0 0 0 0 0","XMP Value Count":"2","Lens Data":"[33 values]","Software":"Ver.1.02","Scene Type":"Directly photographed image","Firmware Version":"2.11","Flash Sync Mode":"NORMAL","FlashPix Version":"1.00","Data Precision":"8 bits","Compressed Bits Per Pixel":"2 bits/pixel","Sub-Sec Time Original":"50","Color Space":"sRGB","Image Data Size":"5636001","Date/Time Digitized":"2016:01:28 16:50:05","Flash":"Flash fired, return detected","Shooting Mode":"Single Frame","AF Tune":"0 255 0 0","Saturation":"None","Flash Bracket Compensation":"0 EV","Vignette Control":"Normal","High ISO Noise Reduction":"Normal","Sharpness":"None","Image Width":"6016 pixels","Resolution Unit":"Inch","Exposure Bias Value":"0 EV","Subject Distance Range":"Unknown","Max Aperture Value":"f/4.0","Exposure Mode":"Manual exposure","Scene Capture Type":"Standard","Custom Rendered":"Normal process","Picture Control":"[68 values]"}},{"key":"0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg","size":2609152,"lastModified":1476151682000,"error":"java.io.IOException: Server returned HTTP response code: 403 for URL: http://s3.amazonaws.com/waldo-recruiting/0015A5C3-D186-471J-A032-9E952CFF3CC6.8fedf4a8-8695-4d6d-ad1e-b686daa713a2.jpg","exifData":{}}]}');
    //
    // $.each(photos_json.photos, function(val, text) {
    //     $('#combobox').append($('<option></option>').val(text.key).html(text.key));
    // });


    // Load the photo data from photos.json
    $.getJSON('photos.json', function(data) {
        photos_json = data;

        $.each(data.photos, function(val, text) {
            $('#combobox').append($('<option></option>').val(text.key).html(text.key));
        });
    });


    // Load the new photo and data when the photo selector changes
    $("#combobox").change(function() {
        $("#error-div").html("");
        var str = $("#combobox option:selected").text();

        // Find the photo object matching the selected key
        var photo;
        $.each(photos_json.photos, function(i, v) {
            if (v.key == str) {
                photo = v;

                $(".image-wrapper").hide();
                $("#image-loading").show();
                // Update the image source with the new photo
                $("#image").attr("src", "http://s3.amazonaws.com/waldo-recruiting/" + str);

                $("#file-size").html(humanFileSize(photo.size));

                if (photo.error) {
                    $("#error-div").html(photo.error);
                    if ($.fn.DataTable.isDataTable('#meta-data')) {
                        $('#meta-data').dataTable().fnClearTable();
                    }
                } else {
                    // Create an array from the photo's exif data
                    var arr = [];
                    $.each(photo.exifData, function(i, v) {
                        arr.push([i, v]);
                    });

                    // Create a datatable using the exif data array
                    // Initialize the table if it is not a DataTable already
                    if (!$.fn.DataTable.isDataTable('#meta-data')) {
                        $('#meta-data').DataTable({
                            destroy: true,
                            data: arr,
                            columns: [{
                                    title: "Key"
                                },
                                {
                                    title: "Value"
                                }
                            ],
                            "columnDefs": [{
                                    className: "dt-left key-col",
                                    "targets": [0]
                                },
                                {
                                    className: "dt-left",
                                    "targets": [1]
                                },
                                {
                                    // Wrap the value cells in a div so we can control the max-height
                                    render: function(data, type, full, meta) {
                                        return "<div class='val-col'>" + data + "</div>";
                                    },
                                    targets: 1
                                }
                            ],
                        });
                    }
                    // The table was previously initialized. Clear and update the table's data with the selected photo's exif data
                    // This keeps any changed table settings (like page size) consitent between photos
                    else {
                        $('#meta-data').dataTable().fnClearTable();
                        $('#meta-data').dataTable().fnAddData(arr);
                    }
                }

                return false;
            }
        });
    });
});

/**
 * Format bytes into a human-readable file size value
 */
function humanFileSize(bytes) {
    if (Math.abs(bytes) < 1000) {
        return bytes + ' B';
    }
    var units = ['kB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    var u = -1;
    do {
        bytes /= 1000;
        ++u;
    } while (Math.abs(bytes) >= 1000 && u < units.length - 1);
    return bytes.toFixed(1) + ' ' + units[u];
}
