package com.example.bspicvote

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
class VoteController(val kvService: KVDbService) {


    @GetMapping(path = ["vote.html"])
    fun test(model: Model,
             @RequestParam("currentPos", required = false) currentPosParam: Optional<Int>,
             @RequestParam("vote", required = false) voteParam: Optional<Boolean>): String {
        val nextPos = currentPosParam.orElseGet { 0 } + 1
        val vote = voteParam.orElseGet { false }

        var imageName: String
        if(currentPosParam.isPresent) {
            imageName = Optional.ofNullable(imageList.getOrNull(nextPos)).orElse("FINISHED.jpg")
            model.addAttribute("currentPos", nextPos)
        } else {
            imageName = Optional.ofNullable(imageList.getOrNull(0)).orElse("FINISHED.jpg")
            model.addAttribute("currentPos", 0)
        }


        if(vote){
            kvService.incrementKey(imageName.substringBefore("."))
        }

        //lookup next image name from 'nextPos' and set
        model.addAttribute("voteimg", "https://storage.googleapis.com/bs-spartans/$imageName")

        println(model.asMap())

        return "vote"
    }

    val imageList= listOf(
            "1.jpg"
    )


}