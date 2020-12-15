/*
A recursive search for all possible moves

To speed up, I don't copy game state (the board) to each node in the search tree,
but instead modify ONE state, and rebuild it when going up the tree.
This gave a huge speedup in javascript, making it not time out.
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

function doMove(oldWhite, board, direction) {
    //board = clone(board);
    var white = {row: oldWhite.row, col: oldWhite.col};
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

    if (board[jumpRow][jumpCol] != 'B' || board[newRow][newCol] != ' ') {
        return 0;
    }

    var oldJump = board[jumpRow][jumpCol];
    board[jumpRow][jumpCol] = ' ';

    board[newRow][newCol] = 'W';
    board[white.row][white.col] = ' ';
    white.row = newRow;
    white.col = newCol;

    var maxForWhite = findMaxForWhite(white, board);


    board[jumpRow][jumpCol] = oldJump;
    board[white.row][white.col] = ' ';
    board[oldWhite.row][oldWhite.col] = 'W';

    return 1 + maxForWhite;

}
