import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlin.concurrent.thread

class VoiceRecorder(private val callback: Callback) {

    interface Callback {
        fun onVoiceStart()
        fun onVoice(data: ByteArray, size: Int)
        fun onVoiceEnd()
    }

    private var audioRecord: AudioRecord? = null
    private var isRecording = false

    fun startRecording() {
        val minBufferSize = AudioRecord.getMinBufferSize(
            SAMPLE_RATE,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            SAMPLE_RATE,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            minBufferSize
        )

        audioRecord?.startRecording()
        isRecording = true

        thread(start = true) {
            val buffer = ByteArray(BUFFER_SIZE)
            while (isRecording) {
                val bytesRead = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                callback.onVoice(buffer, bytesRead)
            }
            audioRecord?.stop()
            audioRecord?.release()
            callback.onVoiceEnd()
        }
    }

    fun stopRecording() {
        isRecording = false
    }

    companion object {
        private const val SAMPLE_RATE = 16000
        private const val BUFFER_SIZE = 1024
    }
}
