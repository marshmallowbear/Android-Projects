import flask
from flask import request, jsonify
import sqlite3

app = flask.Flask(__name__)
app.config['Debug'] = True


# convert each row to dict
def dict_factory(c, row):
    d = {}
    for id, col in enumerate(c.description):
        d[col[0]] = row[id]
    return d


@app.errorhandler(404)
def page_not_found(ex):
    return '<h1>AW MAN</h1><p>Sorry our bakers are busy trying to find the cake you are looking for!</p>'


@app.route('/', methods=['GET'])
def index():
    return '<h1>Hello and welcome to the index method!<h1>'


@app.route('/cakes/all', methods=['GET'])
def allcakes():
    return jsonify(cakelist)


@app.route('/cakes', methods=['GET'])
@app.route('/cakes/<id>', methods=['GET'])
def onecake(id='cake00'):
    if 'id' in request.args:
        id = request.args['id']
    results = []
    for c in cakelist:
        if c['id'] == id:
            results.append(c)

    return jsonify(results)

@app.route('/api/cake', methods= ['POST'])
def add_cake():
    try:
        id = request.form['id']
    except:
        return 'cannot get id'
    try:
        flavour = request.form['flavour']
    except:
        return 'cannot get flavour'
    try:
        bought = int(request.form['bought'])
    except:
        return 'cannot get bought'
    try:
        appearance = request.form['appearance']
    except:
        return 'cannot get appearance'
    try:
        weight = float(request.form['weight'])
    except:
        return 'cannot get weight'

    t = (id, flavour, bought, appearance, weight)
    #return str(t)

    query = "INSERT into cakes VALUES (?,?,?,?,?)"

    try:
        conn = sqlite3.connect('cakes.db')
        conn.row_factory = dict_factory
        #c = conn.cursor
        conn.execute(query, t)
    except:
        return 'cannot execute'
    try:
        conn.commit()
        conn.close()
        return jsonify({'status' : 'successfully added cake!'})
    except:
        conn.close()
        return 'cannot commit'
    
@app.route('/api/cake/<id>', methods=['PUT'])
def cake_update(id):
    new_id = request.form['id']
    new_flavour = request.form['flavour']
    new_appearance = request.form['appearance']
    new_bought = int(request.form['bought'])
    new_weight = float(request.form['weight'])
    t = (new_id, new_flavour, new_bought, new_appearance, new_weight, id)

    query = 'UPDATE cakes SET id = ?, flavour = ?, weight = ?, appearance = ?, weight=? WHERE id = ?'

    conn = sqlite3.connect('cakes.db')
    conn.row_factory = dict_factory
    conn.execute(query, t)
    conn.commit()
    conn.close()
    return jsonify({'status' : 'Update Successful'})

@app.route('/api/cake/<id>', methods = ['DELETE'])
def remove_cake_id(id):
    conn = sqlite3.connect('cakes.db')
    conn.row_factory = dict_factory
    conn.execute('DELETE from cakes WHERE id = ?', (id,))
    conn.commit()
    conn.close()
    return jsonify({'status' : 'delete successful!'})

@app.route('/api/cakes', methods=['GET'])
def cakes_filter():
    conn = sqlite3.connect('cakes.db')
    conn.row_factory = dict_factory
    c = conn.cursor()

    arglist = list(request.args.keys())
    if len(arglist) == 0:
        return page_not_found(404)
    query = "SELECT * from cakes WHERE "
    l = []
    for key in arglist:
        value = request.args[key]
        query += key + "=? AND "
        l.append(value)
    #process query string
    query = query[:-4]
    #execute query
    rows = c.execute(query, l).fetchall()
    conn.close()
    return jsonify(rows)
    
    """ if 'id' in request.args:
        id = request.args['id']
        row = c.execute('SELECT * FROM cakes WHERE id=?', (id,)).fetchall()
        return jsonify(row)
    if 'bought' in request.args or 'weight' in request.args:
        boughtweightquery = 'SELECT * FROM cakes WHERE '
        t = []
        if 'bought' in request.args:
            bought = int(request.args['bought'])
            boughtweightquery += 'bought = ? AND '
            t.append(bought)
        if 'weight' in request.args:
            weight = float(request.args['weight'])
            boughtweightquery += 'weight = ? AND '
            t.append(weight)
        query = boughtweightquery[:-4]
        rows = c.execute(query, t).fetchall()
        return jsonify(rows)
    else:
        return page_not_found(404) """
    

@app.route('/api/cakes/all')
def api_allcakes():
    conn = sqlite3.connect('cakes.db')
    conn.row_factory = dict_factory
    c = conn.cursor()

    rows = c.execute('SELECT * FROM cakes').fetchall()
    return jsonify(rows)


# data
cakelist = [
    {
        'id': 'cake01',
        'flavour': 'strawberry',
        'bought': True,
        'appearance': 'pink with buttercream and lace frostings',
        'weight': 0.5
    },
    {
        'id': 'cake02',
        'flavour': 'coffee',
        'bought': False,
        'appearance': 'mocha coloured with cappucino ombre',
        'weight': 1.2
    },
    {
        'id': 'cake03',
        'flavour': 'rich chocolate ganache',
        'bought': True,
        'appearance': 'thick velvety chocolate atop a creamy chocolate sponge',
        'weight': 1.0
    },
    {
        'id': 'cake04',
        'flavour': 'cheesy cheese',
        'bought': False,
        'appearance': 'double fromage cream cheese with swirly hues',
        'weight': 0.5
    },
    {
        'id': 'cake05',
        'flavour': 'plain vanilla',
        'bought': True,
        'appearance': 'simple plain old vanilla cake with vanilla bean specks on it',
        'weight': 1.2
    },
    {
        'id': 'cake07',
        'flavour': 'chocolate truffles',
        'bought': False,
        'appearance': 'chocolate truffle adorned cake overflowing with artisan chocolates',
        'weight': 1.2
    }

]

app.run()
