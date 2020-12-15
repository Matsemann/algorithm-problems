/*
   TOO SLOW, draughts_noclone works
 */


function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
'4',
'',
'.#.#.#.#.#',
'#.#.#.#.#.',
'.#.#.B.#.#',
'#.#.#.#.#.',
'.#.#.B.#.#',
'#.#.W.#.#.',
'.#.#.#.#.#',
'#.#.#.B.#.',
'.#.#.#.#.#',
'#.#.#.#.#.',
'',
'.W.#.W.#.#',
'#.#.#.#.#.',
'.#.#.B.#.#',
'#.B.#.W.#.',
'.W.#.B.#.#',
'#.B.W.#.#.',
'.#.B.B.#.#',
'#.W.#.#.#.',
'.#.B.B.#.#',
'#.#.#.W.#.',
'',
'.#.#.#.#.#',
'#.#.#.#.#.',
'.B.B.B.B.#',
'#.#.#.#.#.',
'.B.B.B.B.#',
'#.#.#.#.#.',
'.#.B.B.B.#',
'#.#.#.#.#.',
'.#.B.B.#.#',
'#.W.W.W.#.',
'',
'.#.#.#.#.#',
'#.#.B.#.#.',
'.#.#.W.#.#',
'#.#.B.B.#.',
'.#.#.#.#.#',
'#.#.B.B.#.',
'.#.#.#.#.#',
'#.#.#.#.#.',
'.#.#.#.#.#',
'#.W.W.W.#.'
];


function readline() {
    return input[readCount++];
}


function draughts() {
    var games = parseInt(readline());
    for (var i = 0; i < games; i++) {
        solveBoard();
    }
}
draughts();

function makeBoard() {
    readline();
    var board = [];
    var whites = [];

    for (var i = 0; i < 10; i++) {
        var row = [];

        var spots = readline().split('');
        for (var j = 0; j < 10; j++) {
            var b = spots[j];
            if (b == 'W') {
                whites.push({row: i, col: j});
            }

            if (b == 'W' || b == 'B') {
                row.push(b);
            } else {
                row.push(' ');
            }
        }
        board.push(row);
    }

    return {
        board: board,
        whites: whites
    };
}

function solveBoard() {
    var data = makeBoard();
    var whites = data.whites;
    var board = data.board;

    var max = 0;
    for (var i = 0; i < whites.length; i++) {
        var moves = findMaxForWhite(whites[i], board);
        if (moves > max) {
            max = moves;
        }
    }

    print(max);
}

function findMaxForWhite(white, board) {

    return Math.max(
        doMove(white, board, 'ul'),
        doMove(white, board, 'ur'),
        doMove(white, board, 'dl'),
        doMove(white, board, 'dr')
    );

}
/*
readline();
makeBoard();
print(doMove({row: 5, col: 10}, 0, 'ul'));*/
/*
readline();
solveBoard();*/

function doMove(white, board, direction) {
    board = clone(board);
    white = {row: white.row, col: white.col};
    var newCol, newRow, jumpCol, jumpRow;
    if (direction.charAt(0) == 'u') {
        newRow = white.row - 2;
        jumpRow = white.row - 1;
    } else {
        newRow = white.row + 2;
        jumpRow = white.row + 1;
    }
    if (direction.charAt(1) == 'l') {
        newCol = white.col - 2;
        jumpCol = white.col - 1;
    } else {
        newCol = white.col + 2;
        jumpCol = white.col + 1;
    }
    if (jumpRow > 9 || jumpCol > 9 || newCol > 9 || newRow > 9 || jumpRow < 0 || jumpCol < 0 || newCol < 0 || newRow < 0) {
        return 0;
    }

    if (board[jumpRow][jumpCol] != 'B') {
        return 0;
    } else {
        board[jumpRow][jumpCol] = ' ';
    }

    if (board[newRow][newCol] != ' ') {
        return 0;
    } else {
        board[newRow][newCol] = 'W';
        board[white.row][white.col] = ' ';
        white.row = newRow;
        white.col = newCol;
    }

    return 1 + findMaxForWhite(white, board);

}

function clone(board) {
    var newBoard = [];
    for (var i = 0; i < 10; i++) {
        var newRow = [];
        for (var j = 0; j < 10; j++) {
            newRow.push(board[i][j]);
        }
        newBoard.push(newRow);
    }
    return newBoard;
    //return JSON.parse(JSON.stringify(board));
}
