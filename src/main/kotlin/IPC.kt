private object IPC {
    val handles: HashMap<Any, HashMap<String, (Any?) -> Unit>> = HashMap()
}

data class SignalAssociation(val receiver: Any, val signal: String)
data class SophisticatedSignal(val name: String, val parameters: Any?)


// Event broadcasting
infix fun Any.broadcast(signal: String) = IPC.handles.filter { (_, h) -> h.contains(signal) }.forEach { (r, _) -> r consume signal }

infix fun Any.broadcast(signal: SophisticatedSignal) = IPC.handles.filter { (_, h) -> h.contains(signal.name) }.forEach { (r, _) -> r consume signal }

// Event consumption
infix fun Any.consume(signal: String) = IPC.handles[this]?.get(signal)?.invoke(null)

infix fun Any.consume(signal: SophisticatedSignal) = IPC.handles[this]?.get(signal.name)?.invoke(signal.parameters)


// Event listen & stop listen
infix fun Any.listenTo(signal: String): SignalAssociation {
    if (IPC.handles[this] == null)
        IPC.handles[this] = HashMap()
    return SignalAssociation(this, signal)
}

infix fun SignalAssociation.then(block: (Any?) -> Unit) = IPC.handles[receiver]?.put(signal, block)
infix fun Any.stopListenTo(signal: String) =  IPC.handles[this]?.remove(signal)

