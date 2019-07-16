import sqlite3

conn = sqlite3.connect('cakes.db')
c = conn.cursor()

c.execute('CREATE table cakes (id text, flavour text, bought integer, appearance text, weight real)')
c.execute("INSERT into cakes VALUES ('cake01','strawberry', 1, 'pink with buttercream and lace frostings', 0.5)")
c.execute("INSERT into cakes VALUES ('cake02','chocolate ganache', 0, 'deep gooey chocoate coating atop chocolate sponge', 1.0)")
c.execute("INSERT into cakes VALUES ('cake03','coffee mocha', 1, 'capuccino buttercream gradient with coffee beans atop', 1.2)")
c.execute("INSERT into cakes VALUES ('cake04','cheesey cheesecake', 0, 'classic new york cheesecake with cookie base', 1.0)")
c.execute("INSERT into cakes VALUES ('cake05','strawberry mousse', 0, 'strawberry flavoured jelly mousse with raspberry cream', 0.5)")
c.execute("INSERT into cakes VALUES ('cake06','chocolate truffle', 1, 'chocolate gaanche with artisan chocolates atop', 1.0)")
conn.commit()
conn.close()