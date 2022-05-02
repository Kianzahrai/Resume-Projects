import pyttsx3
import speech_recognition as sr
import webbrowser
import wikipedia

def speak(audio):
    engine = pyttsx3.init()
    voices = engnie.getProperty('voices')
    engine.setProperty('voice', voices[0].id)
    engine.say(audio)
    engine.runAndWait()

def Hello():
    speak("""Hello sir, I am your desktop assistant. Tell me, how may I be of assist""")

def takeCommand():
    r = sr.Recognizer()
    with sr.Microphone() as source:
        print('Listening')
        r.pause_threshold = 0.7
        audio = r.listen(source)
        try:
            print("Recognizing")
            Query = r.recognize_google(audio.language = 'en-in')
            print("the command in printed = ", Query)
        except Exception as e:
            print(e)
            print("Could you repeat that sir")
            return None
        return Query

def Take_query():
    Hello()
    while(True):
        query = takeCommand().lower()
        if "Hello how are your" in query:
            speak("I am Fine")
        elif "open google" in query:
            speak("Opening Google")
            webbrowser.open("www.google.com")
        # this will exit and terminate the program
        elif "bye" in query:
            speak("Okay Bye.")
            exit()
        elif "from wikipedia" in query:
            speak("Checking the wikipedia ")
            query = query.replace("wikipedia", "")
            result = wikipedia.summary(query, sentences = 2)
            speak("According to wikipedia")
            speak(result)
        elif "tell me your name" in query:
            speak("I am Alfred, your desktop assistant.")

if __name__ == '__main__':
    # main method for executing the functions
    Take_query()
