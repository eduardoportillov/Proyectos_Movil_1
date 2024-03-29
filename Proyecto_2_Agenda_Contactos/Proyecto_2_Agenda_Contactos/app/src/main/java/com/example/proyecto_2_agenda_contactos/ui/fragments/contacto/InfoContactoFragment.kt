package com.example.proyecto_2_agenda_contactos.ui.fragments.contacto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.datasingleton.ContactoItem
import com.example.proyecto_2_agenda_contactos.models.Contacto
import com.example.proyecto_2_agenda_contactos.models.Telefono


class InfoContactoFragment : Fragment() {

    val args: InfoContactoFragmentArgs by navArgs()

    lateinit var labelContactoNombre: TextView
    lateinit var labelContactoApellido: TextView
    lateinit var labelContactoCiudad: TextView
    lateinit var labelContactoEdad: TextView
    lateinit var labelContactoEmail: TextView
    lateinit var labelContactoDireccion: TextView
    lateinit var fragmentTelefonoList: FragmentContainerView
    lateinit var btnDeleteContacto: ImageButton
    lateinit var btnEditContacto: ImageButton
    lateinit var btnAddTelefono: ImageButton
    lateinit var contactoAux: Contacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sendTelefono(contactoAux)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_info_contacto, container, false)
        val contactoRecibido: Contacto = args.contactoEnviado
        contactoAux = contactoRecibido
//        val action = InfoContactoFragmentDirections.actionInfoContactoFragmentToTelefonoFragment(contactoRecibido)
//        fragmentTelefonoList = view.findViewById(R.id.fragmentTelefonoList)


        labelContactoNombre = view.findViewById(R.id.labelContactoNombre)
        labelContactoApellido = view.findViewById(R.id.labelContactoApellido)
        labelContactoCiudad = view.findViewById(R.id.labelContactoCiudad)
        labelContactoEdad = view.findViewById(R.id.labelContactoEdad)
        labelContactoEmail = view.findViewById(R.id.labelContactoEmail)
        labelContactoDireccion = view.findViewById(R.id.labelContactoDireccion)

        btnDeleteContacto = view.findViewById(R.id.btnDeleteContacto)
        btnEditContacto = view.findViewById(R.id.btnEditContacto)
        btnAddTelefono = view.findViewById(R.id.btnAddTelefono)


        setupFormContacto(contactoRecibido)
        setupButtonClick(contactoRecibido)
        return view
    }

    private fun sendTelefono(contacto: Contacto) {
        setFragmentResult(
            "dataInfoContactoFragment",
            bundleOf("contactoEnviado" to contacto)
        ) //TODO intentando mandar datos de un fragment a otro
    }

    private fun setupFormContacto(contactoRecibido: Contacto) {
        labelContactoNombre.text = contactoRecibido.nombre
        labelContactoApellido.text = contactoRecibido.apellido
        labelContactoCiudad.text = contactoRecibido.ciudad
        labelContactoEdad.text = contactoRecibido.edad.toString()
        labelContactoEmail.text = contactoRecibido.email
        labelContactoDireccion.text = contactoRecibido.direccion
    }

    private fun setupButtonClick(contactoRecibido: Contacto) {
        btnDeleteContacto.setOnClickListener {
            ContactoItem.removeContact(contactoRecibido)
            findNavController().navigate(R.id.contactoFragment)
        }

        btnEditContacto.setOnClickListener {
            val action =
                InfoContactoFragmentDirections.actionInfoContactoFragmentToEditFormContactoFragment(
                    contactoRecibido
                )
            findNavController().navigate(action)
        }

        btnAddTelefono.setOnClickListener {
            val action =
                InfoContactoFragmentDirections.actionInfoContactoFragmentToFormTelefonoFragment(
                    contactoRecibido.id
                )
            findNavController().navigate(action)
        }
    }
}