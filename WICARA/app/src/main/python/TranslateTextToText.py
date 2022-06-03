Dataset = {
    "Dataset_14" :
    {
    "saya":"aku",
    "makan":"mangan",
    "nasi":"sego",
    "minum":"ngombe",
    "air":"banyu"
    },
    "Dataset_41" :
    {
    "aku":"saya",
    "mangan":"makan",
    "sego":"nasi",
    "ngombe":"minum",
    "banyu":"air"
    }
}

def translate_bahasa(teks, dataset):
  dt = Dataset[dataset]
  text_list=[]
  for word in teks.split():
    if word in dt:
      word = word.replace(word, str(dt[word]))
      text_list.append(word)
    else:
      text_list.append(word)
  teks=' '.join(map(str, text_list))
  return teks