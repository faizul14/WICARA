import sounddevice
from scipy.io.wavfile import write

frekuensi_sample = 44100
waktu_rekam = 1.5

print("Recording.....\n")

record_voice = sounddevice.rec(
    int(waktu_rekam * frekuensi_sample), samplerate=frekuensi_sample, channels=2)
sounddevice.wait()
write("out2.wav", frekuensi_sample, record_voice)
print("Finished.....\nPlease check your output file")
