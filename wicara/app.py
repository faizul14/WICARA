import random
import os
from flask import Flask, request, jsonify
from kssjawa import Keyword_Spotting_Service_Jawa
from kssbali import Keyword_Spotting_Service_Bali
from kssbatak import Keyword_Spotting_Service_Batak
from kssmadura import Keyword_Spotting_Service_Madura
from kssmelayu import Keyword_Spotting_Service_Melayu
from kssminang import Keyword_Spotting_Service_Minang
from ksssunda import Keyword_Spotting_Service_Sunda
app = Flask(__name__)

@app.route('/')
def index():
    json = {
        "message": "Hello World!"
    }
    return jsonify(json)

@app.route('/testing', methods=['POST'])
def testing():
    data = request.get_json()
    return jsonify(data)

@app.route("/predictjawa", methods=["POST"])
def predict_jawa():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Jawa()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictbali", methods=["POST"])
def predict_bali():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Bali()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictbatak", methods=["POST"])
def predict_batak():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Batak()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictmadura", methods=["POST"])
def predict_madura():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Madura()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictmelayu", methods=["POST"])
def predict_melayu():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Melayu()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictminang", methods=["POST"])
def predict_minang():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Minang()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

@app.route("/predictsunda", methods=["POST"])
def predict_sunda():
	# get file from POST request and save it
	audio_file = request.files["file"]
	file_name = str(random.randint(0, 100000))
	audio_file.save(file_name)

	# instantiate keyword spotting service singleton and get prediction
	kss = Keyword_Spotting_Service_Sunda()
	predicted_keyword = kss.predict(file_name)

	os.remove(file_name)

	# send back result as a json file
	result = {
        "keyword": predicted_keyword
        }
	return jsonify(result)

if __name__ == "__main__":
    app.run(debug=False)