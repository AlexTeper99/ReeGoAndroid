package com.example.reegoandroid.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleUserViewModel
import com.google.android.material.snackbar.Snackbar

class SingleUserFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle     : TextView

    private lateinit var txtName      : TextView
    private lateinit var txtEmail     : TextView
    private lateinit var txtPassword  : TextView
    private lateinit var txtCity      : TextView
    private lateinit var txtPlotDesc  : TextView

    lateinit var btnCreateUser: Button
    lateinit var btnUpdateUser: Button
    lateinit var btnDeleteUser: Button


    private val singleUserViewModel : SingleUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_single_user, container, false)

        txtTitle = v.findViewById(R.id.txtSingleUserTitle)

        // Setup Input Fields

        txtName      = v.findViewById(R.id.editTextUserName)
        txtEmail     = v.findViewById(R.id.editTextUserEmail)
        txtPassword  = v.findViewById(R.id.editTextUserPassword)
        txtCity      = v.findViewById(R.id.editTextCity)
        txtPlotDesc  = v.findViewById(R.id.editTextPlotDescription)

        // Setup Buttons
        btnCreateUser = v.findViewById(R.id.createNewUserBtn)
        btnUpdateUser = v.findViewById(R.id.updateUserBtn)
        btnDeleteUser = v.findViewById(R.id.deleteUserBtn)

        return v
    }

    override fun onStart() {
        super.onStart()


        val userId      = SingleUserFragmentArgs.fromBundle(requireArguments()).userId
        var userName    = SingleUserFragmentArgs.fromBundle(requireArguments()).userName
        var userEmail   = SingleUserFragmentArgs.fromBundle(requireArguments()).userEmail
        var userPass    = SingleUserFragmentArgs.fromBundle(requireArguments()).userPassword
        var plotCity    = SingleUserFragmentArgs.fromBundle(requireArguments()).userCity
        var userIsAdmin = SingleUserFragmentArgs.fromBundle(requireArguments()).userIsAdmin

        var userPlotDesc  = SingleUserFragmentArgs.fromBundle(requireArguments()).userPlotDesc
        var cropType = SingleUserFragmentArgs.fromBundle(requireArguments()).userCropType

        var isEdit = SingleUserFragmentArgs.fromBundle(requireArguments()).isEdit

        if(isEdit){
            btnCreateUser.isVisible = false
        }else{
            btnDeleteUser.isVisible = false
            btnUpdateUser.isVisible = false
        }

        txtTitle.text = "Editar Usuario"

        txtName.text      = userName
        txtEmail.text     = userEmail
        txtPassword.text  = userPass
        txtCity.text      = plotCity

//        txtIsAdmin.isVisible = false

        txtPlotDesc.text = userPlotDesc


        // Setup Spinner to select crop type
        val cropTypes   = arrayOf("Trigo","Soja", "Maiz")
        val cropSpinner : Spinner = v.findViewById(R.id.cropTypeSpinner)

        val spinnerAdapter = ArrayAdapter(requireContext(),
        android.R.layout.simple_spinner_item, cropTypes)

        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        cropSpinner.adapter = spinnerAdapter
        // set the position of the spinner = to the string given by argument
        cropSpinner.setSelection(spinnerAdapter.getPosition(cropType))

        // CREATE a user
        btnCreateUser.setOnClickListener {

            var userName    = txtName.text.toString()
            var userEmail   = txtEmail.text.toString()
            var userPass    = txtPassword.text.toString()
            var userIsAdmin = false
            //var userIsAdmin = false
            var plotCity    = txtCity.text.toString()
            var plotDesc    = txtPlotDesc.text.toString()
            var cropType    = cropSpinner.selectedItem.toString()

            var validInputs : Boolean = singleUserViewModel.validateInputs(
                userName,
                userEmail,
                userPass,
                plotCity,
                plotDesc,
                cropType,
            )

            if(validInputs) {
                // create user
                singleUserViewModel.createUser(
                    userName,
                    userEmail,
                    userPass,
                    userIsAdmin,
                    plotCity,
                    plotDesc,
                    cropType,
                )
                // redirect
                v.findNavController().navigate(SingleUserFragmentDirections.actionSingleUserFragment2ToBackofficeFragment())
            } else {
                // invalid inputs
                var snackbar =  Snackbar.make(v, "Todos los campos son requeridos",
                    Snackbar.LENGTH_SHORT).setAction("Action", null)
                var sbView = snackbar.view
                sbView.setBackgroundColor(Color.RED)
                snackbar.show()
            }

        }

        // UPDATE a user
        btnUpdateUser.setOnClickListener {

            var userName    = txtName.text.toString()
            var userEmail   = txtEmail.text.toString()
            var userPass    = txtPassword.text.toString()
            var userIsAdmin = false
            // var userIsAdmin = true
            var plotCity    = txtCity.text.toString()
            var plotDesc    = txtPlotDesc.text.toString()
            var cropType    = cropSpinner.selectedItem.toString()

            var validInputs : Boolean = singleUserViewModel.validateInputs(
                userName,
                userEmail,
                userPass,
                plotCity,
                plotDesc,
                cropType,
            )
            if(validInputs) {
                //  update the user
                singleUserViewModel.updateUser(
                    userId,
                    userName,
                    userEmail,
                    userPass,
                    userIsAdmin,
                    plotCity,
                    plotDesc,
                    cropType
                )
                // redirect
                v.findNavController().navigate(SingleUserFragmentDirections.actionSingleUserFragment2ToBackofficeFragment())
            } else {
                // invalid inputs
                var snackbar =  Snackbar.make(v, "Complete todos los campos",
                    Snackbar.LENGTH_SHORT).setAction("Action", null)
                var sbView = snackbar.view
                sbView.setBackgroundColor(Color.RED)
                snackbar.show()
            }
        }

        // DELETE a user
        btnDeleteUser.setOnClickListener {
            singleUserViewModel.deleteUser(userId)
            v.findNavController().navigate(SingleUserFragmentDirections.actionSingleUserFragment2ToBackofficeFragment())

        }

    }

}