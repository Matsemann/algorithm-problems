function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
    '3029',
    '4',
    '5',
    '42',
    '0'
];

function readline() {
    return input[readCount++];
}


function easiest() {
    var N = parseInt(readline());

    while (N != 0) {

        var p = 11;

        var sumN = sumOfNrs(N);

        while (sumN != sumOfNrs(N * p)) { // noprotect
            p++;
        }
        print(p);

        N = parseInt(readline());
    }

    function sumOfNrs(nr) {
        var str = (nr + '').split('');
        var sum = 0;
        for (var i = 0; i < str.length; i++) {
            sum += parseInt(str[i]);
        }
        return sum;
    }
}

easiest();