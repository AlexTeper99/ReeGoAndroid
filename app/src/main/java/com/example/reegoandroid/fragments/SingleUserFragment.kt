package com.example.reegoandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reegoandroid.R
import com.example.reegoandroid.viewmodels.SingleUserViewModel

class SingleUserFragment : Fragment() {
    lateinit var v: View
    private lateinit var txtTitle     : TextView

    private lateinit var txtName      : TextView
    private lateinit var txtEmail     : TextView
    private lateinit var txtPassword  : TextView
    private lateinit var txtIdParcela : TextView
    private lateinit var txtIsAdmin   : CheckBox

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
        txtIdParcela = v.findViewById(R.id.editTextIdParcela)
        txtIsAdmin   = v.findViewById(R.id.userIsAdminCheckbox)

        // Setup Buttons
        btnCreateUser = v.findViewById(R.id.createNewUserBtn)
        btnUpdateUser = v.findViewById(R.id.updateUserBtn)
        btnDeleteUser = v.findViewById(R.id.deleteUserBtn)

        return v
    }

    override fun onStart() {
        super.onStart()

        // Important
        val userId      = SingleUserFragmentArgs.fromBundle(requireArguments()).userId

        var userPlotId  = SingleUserFragmentArgs.fromBundle(requireArguments()).userPlotId
        var userName    = SingleUserFragmentArgs.fromBundle(requireArguments()).userName
        var userEmail   = SingleUserFragmentArgs.fromBundle(requireArguments()).userEmail
        var userPass    = SingleUserFragmentArgs.fromBundle(requireArguments()).userPassword
        var userIsAdmin = SingleUserFragmentArgs.fromBundle(requireArguments()).userIsAdmin

        txtTitle.text = "Editar Usuario"

        txtName.text      = userName
        txtEmail.text     = userEmail
        txtPassword.text  = userPass
        txtIdParcela.text = userPlotId.toString()
        txtIsAdmin.isChecked = userIsAdmin


        // CREATE a user
        btnCreateUser.setOnClickListener {

            var userName    = txtName.text.toString()
            var userEmail   = txtEmail.text.toString()
            var userPass    = txtPassword.text.toString()
            var userPlotId  = txtIdParcela.text.toString().toInt()
            var userIsAdmin = txtIsAdmin.isChecked

            singleUserViewModel.createUser(
                userName,
                userEmail,
                userPass,
                userPlotId,
                userIsAdmin
            )

            // TODO REDIRECT BACK TO USER LIST
        }

        // UPDATE a user
        btnUpdateUser.setOnClickListener {

            var userName    = txtName.text.toString()
            var userEmail   = txtEmail.text.toString()
            var userPass    = txtPassword.text.toString()
            var userPlotId  = txtIdParcela.text.toString().toInt()
            var userIsAdmin = txtIsAdmin.isChecked

            singleUserViewModel.updateUser(
                userId,
                userName,
                userEmail,
                userPass,
                userPlotId,
                userIsAdmin
            )

            // TODO REDIRECT BACK TO USER LIST
        }

        // DELETE a user
        btnDeleteUser.setOnClickListener {
            singleUserViewModel.deleteUser(userId)
            // TODO REDIRECT BACK TO USER LIST
        }

    }

}