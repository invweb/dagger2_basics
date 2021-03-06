package com.example.dagger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dagger.application_graph.ApplicationGraph
import com.example.dagger.application_graph.DaggerApplicationGraph
import com.example.dagger.components.DaggerComponent
import com.example.dagger.dagger_test.TestClass
import com.example.dagger.dagger_test.TestUserClass
import com.example.dagger.databinding.FragmentFirstBinding
import com.example.dagger.user.UserRepository
import javax.inject.Inject
import javax.inject.Named

class FirstFragment : Fragment() {

    private val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    private val userRepository: UserRepository = applicationGraph.repository()
    private val testClass: TestClass = applicationGraph.getTestClass()
    private val testUserClass: TestUserClass = applicationGraph.getTestUserClass()
    private lateinit var fragmentFirstBinding: FragmentFirstBinding

    @Inject
    @Named("test1")
    lateinit var test1String: String

    @Inject
    @Named("test2")
    lateinit var test2String: String

    @Inject
    @Named("TestModuleText")
    lateinit var test2TestModuleText: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        fragmentFirstBinding = FragmentFirstBinding.inflate(layoutInflater)
        return fragmentFirstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerComponent.create().inject(this)
        val data = userRepository.getUserRemoteDataSource().getData()

        val test1 = userRepository.getUserLocalDataSource().getTest1()
        val test2 = userRepository.getUserLocalDataSource().getTest2()

        val testClassName = testClass.getName()
        val testClassUserName = testClass.getTestUserClassName()
        val testClassUserToString = testUserClass.toString()
        val testUserClassGetCassName = testUserClass.getClassName()

        userRepository.testRx()?.subscribe(userRepository.getObserver())

        fragmentFirstBinding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}