package com.benjaminledet.pokedex.ui.pokemon.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.repository.utils.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonDetailFragment: Fragment() {

    private val viewModel by viewModel<PokemonDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pokemonId.postValue(arguments?.getInt("pokemonId"))

        viewModel.refreshState.observe(viewLifecycleOwner, Observer { refreshState ->
            progressBar.isVisible = refreshState.status == Status.RUNNING
            content.isVisible = refreshState.status != Status.RUNNING
        })

        viewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon ->
            activity?.title = pokemon?.name
            weight.text = getString(R.string.pokemon_weight, pokemon?.detail?.weight.toString())
            height.text = getString(R.string.pokemon_height, pokemon?.detail?.height.toString())

            //affichage types pokemon

            val typesPokemon = pokemon?.detail?.types?.joinToString(" - ")
            type.text = getString(R.string.pokemon_type, typesPokemon)

            pokemon?.detail?.let { pokemonDetail ->
                var mainTypePokemon = if(pokemon.detail.types.size > 1){
                    pokemon.detail.types?.get(1)
                } else {
                    pokemon.detail.types.get(0)
                }

                // data HEX COLOR from http://epidemicjohto.forumotion.com/t882-type-colors-hex-colors

                when(mainTypePokemon){
                    "poison" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.poison))
                    "fire" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.fire))
                    "water" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.water))
                    "ground" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.ground))
                    "rock" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.rock))
                    "psychic" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.psychic))
                    "dark" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.dark))
                    "bug" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.bug))
                    "dragon" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.dragon))
                    "grass" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.grass))
                    "ice" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.ice))
                    "ghost" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.ghost))
                    "electric" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.electric))
                    "normal" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.normal))
                    "steal" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.steal))
                    "fairy" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.fairy))
                    "fighting" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.fighting))
                    "flying" -> pokemonContainer.setCardBackgroundColor(requireContext().getColor(R.color.flying))
                    else -> pokemonContainer.setCardBackgroundColor(Color.TRANSPARENT)
                }
            }

            Picasso.get().load(pokemon?.iconUrl).into(icon)
            val animation = AnimationUtils.loadAnimation(this.context, R.anim.bounce)
            icon.startAnimation(animation)

        })

        viewModel.moves.observe(this, Observer { moves ->

            val color = pokemonContainer.cardBackgroundColor.defaultColor

            attackOne.setBackgroundColor(color)
            attackTwo.setBackgroundColor(color)
            attackThree.setBackgroundColor(color)
            attackFour.setBackgroundColor(color)

            if (moves.isNotEmpty())
            {
                var randomOne = 0
                var randomTwo = 0
                var randomThree = 0
                var randomFour = 0

                val end = moves.size - 1

                randomOne = (0..end).shuffled().first()
                randomTwo = (0..end).shuffled().first()
                randomThree= (0..end).shuffled().first()
                randomFour = (0..end).shuffled().first()

                attackOne.text = moves[randomOne].toString()
                attackTwo.text = moves[randomTwo].toString()
                attackThree.text = moves[randomThree].toString()
                attackFour.text = moves[randomFour].toString()
            }
            else
            {
                attackOne.text = "-"
                attackTwo.text = "-"
                attackThree.text = "-"
                attackFour.text = "-"
            }
        })
    }
}