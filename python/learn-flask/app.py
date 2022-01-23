from flask import Flask, render_template
app = Flask(__name__)

@app.route('/')
@app.route('/hochschule')
def hochschule():
    return render_template('hochschule.html')

@app.route('/mentor')
def mentor():
    return render_template('mentor.html')

@app.route('/hochschule/bearbeiten')
def hochschule_bearbeiten():
    return render_template('hochschule_bearbeiten.html')

@app.route('/hochschule/anlegen')
def hochschule_anlegen():
    return render_template('hochschule_anlegen.html')

@app.route('/bericht')
def bericht():
    return render_template('bericht.html')

if __name__ == '__main__':
    app.run(port=5000,debug=True)