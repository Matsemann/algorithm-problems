/*
Simple check for if a queen collides with another queen,

Works by basically checking if row or col is the same
OR if the vertical distance and horizontal distance is the same
as that means they are on the same diagonal

 var vertDst = Math.abs(q1.row - q2.row);
 var colDst = Math.abs(q1.col - q2.col);

 return q1.row == q2.row || q1.col == q2.col || vertDst == colDst;

 */

function print(input) {
    console.log(input);
}

var readCount = 0;
/*
var input = [
'*.......',
'..*.....',
'....*...',
'......*.',
'.*......',
'.......*',
'.....*..',
'...*....'
];
*/
/*
var input = [
'*.......',
'......*.',
'....*...',
'.......*',
'.*......',
'...*....',
'.....*..',
'..*.....'
];
*/
/*
var input = [
    '.......*',
    '..*.....',
    '....*...',
    '......*.',
    '.*......',
    '*.......',
    '.....*..',
    '...*....'
];
*/

var input = [
    '*.......',
    '........',
    '........',
    '........',
    '........',
    '........',
    '.*......',
    '...*....'
];



function readline() {
    return input[readCount++];
}

function readQueenPos(line, row) {
    var queens = [];

    var split = line.split('');
    for (var i = 0; i < split.length; i++) {
        if (split[i] == '*') {
            queens.push({
                row: row,
                col: i
            });
        }
    }

    return queens;
}

function queens() {
    var queens = [];
    for (var i = 0; i < 8; i++) {
        queens = queens.concat(readQueenPos(readline(), i));
    }

    if (queens.length != 8) {
        print('invalid');
        return;
    }

    if(isValidQueenPosition(queens)) {
        print('valid');
    } else {
        print('invalid');
    }
}

function isValidQueenPosition(positions) {
    for (var i = 0; i < positions.length; i++) {
        var q1 = positions[i];
        for (var j = i + 1; j < positions.length; j++) {
            var q2 = positions[j];

            if (queensCollide(q1, q2)) {
                return false;
            }
        }
    }

    return true;
}

function queensCollide(q1, q2) {
    var vertDst = Math.abs(q1.row - q2.row);
    var colDst = Math.abs(q1.col - q2.col);

    return q1.row == q2.row || q1.col == q2.col || vertDst == colDst;
}

queens();

