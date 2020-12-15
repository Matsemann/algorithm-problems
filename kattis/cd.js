/*
Just a naiive check in a hashmap/dict
 */

function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
    '3 3',
    '1',
    '2',
    '3',
    '1',
    '2',
    '4',
    '3 3',
    '1',
    '2',
    '3',
    '1',
    '2',
    '4',
    '0 0'
];


function readline() {
    return input[readCount++];
}

function cd() {

    while (true) {
        var ins = readline();
        if (ins == '0 0') {
            return;
        }

        ins = ins.split(' ');
        var jackCds = parseInt(ins[0]);
        var jillCds = parseInt(ins[1]);

        cdNr(jackCds, jillCds);
    }
}

function cdNr(jackCds, jillCds) {

    var jacks = {};

    for (var i = 0; i < jackCds; i++) {
        var cdNr = parseInt(readline());
        jacks[cdNr] = true;
    }

    var equals = 0;

    for (var j = 0; j < jillCds; j++) {
        var cdNr2 = parseInt(readline());
        if (jacks[cdNr2]) {
            equals++;
        }
    }

    print(equals);
}

cd();