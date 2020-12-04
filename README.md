# This repository showcases various Android animation libraries.

### 1. Loading Button
Android button that uses morph animation to transform into circular progressbar. Easy way to make your application more responsive.
You can find library repository [here](https://github.com/leandroBorgesFerreira/LoadingButtonAndroid). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/ProgressButtonFragment.kt).\
\
![](./gifs/progress_button.gif)
### 2. TransformationLayout
Transform view into another view using morph animation. You can also morph between two activities or fragments.
This is very nice looking effect that helps your user to better understand changes happening on the screen.
TransformationLayout uses *MaterialContainerTransform* from com.google.android.material:material to achieve the effect.
You can find library repository [here](https://github.com/skydoves/TransformationLayout). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/TransformationFragment.kt).\
\
![](./gifs/transformation.gif)
### 3. Spring animation
Use this if you need physics based animation. You can customize spring's stiffness, damping ratio and its final position to achieve real like motion.
You can find documentation [here](https://developer.android.com/reference/androidx/dynamicanimation/animation/SpringAnimation). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/SpringFragment.kt).\
\
![](./gifs/spring.gif)
### 4. Fling animation
In fling animation you can customize friction and starting velocity. Friction force will be proportional to object's velocity.
You can find documentation [here](https://developer.android.com/guide/topics/graphics/fling-animation). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/FlingFragment.kt).\
\
![](./gifs/fling.gif)
### 5. Animate layout changes
`android:animateLayoutChanges` flag is a great and easy way to breathe life into your application. We can use this flag on any *ViewGroup*.
 Android framework will then automatically animate layout changes caused by any of the following actions:
- adding view
- removing view
- changing view visibility
- resizing view
<!-- -->
We can also customize animation by modifying *LayoutTransition* object. Have in mind that this API is quite old and limited so you won't be able to achieve every effect you wish for.
You can find documentation [here](https://developer.android.com/reference/android/animation/LayoutTransition). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/LayoutChangesFragment.kt).\
\
![](./gifs/animate_layout_changes.gif)
### 6. Transitions API
Transition framework makes it easy to animate various changes in your application’s UI by simply providing the starting layout and the ending layout. You can describe what kind of animations you would like to use (fade in, fade out, translation) and the transition library will figure out how to animate from the starting layout to the ending layout.
You can use transition framework to animate between activities or fragments. You also animate simple layout changes.
We can animate layout changes simply by calling
```
TransitionManager.beginDelayedTransition(view)
```
We can specify custom transitions for concrete views and control animation order.
We might also want to create custom transitions if required animation isn’t supported by one of the default transitions.
Have in mind that Transitions API has some limitations:
- Animations applied to *SurfaceView* or *TextureView* might not be displayed correctly.
- Classes extending *AdapterView* like *ListView* are incompatible with Transitions framework.
- If you resize widget with text then text will not be animated. It will just pop to the location
<!-- -->
You can find documentation [here](https://developer.android.com/training/transitions). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/TransitionLayoutChangesFragment.kt).\
\
![](./gifs/transitions.gif)
### 7. Transitions API - Scenes
To animate between two layouts with Transitions framework you should use scenes API.
To create animation:
1. Create two *Scene* objects - one for the starting layout and second one for ending layout.
2. Create *Transition* object. You customize animations and order.
3. Invoke *TransitionManager.go()*
<!-- -->
\
![](./gifs/scenes.png)
\
\
You can find documentation [here](https://developer.android.com/reference/android/transition/Scene). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/SceneFragment.kt).\
\
![](./gifs/scenes.gif)
### 8. ConstraintSet
You can find documentation [here](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintSet). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/ConstraintSetFragment.kt).\
\
![](./gifs/constraint.gif)
### 9. Activity transition
You can find documentation [here](https://developer.android.com/training/transitions/start-activity). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/ActivityTransitionFragment.kt).\
\
![](./gifs/activity_transition.gif)
### 10. MotionLayout - basics
You can find documentation [here](https://developer.android.com/training/constraint-layout/motionlayout). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/MotionLayoutFragment.kt).\
\
![](./gifs/motion.gif)
### 11. MotionLayout - key cycles
You can find documentation [here](https://developer.android.com/reference/androidx/constraintlayout/motion/widget/MotionLayout#keycycle). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/OtherMotionLayoutFragment.kt).\
\
![](./gifs/key_cycles.gif)
### 12. Side panel
Illustrates motion layout usage in practice Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/SidePanelFragment.kt).\
\
![](./gifs/side_panel.gif)
### 13. Vector drawable
You can find documentation [here](https://developer.android.com/guide/topics/graphics/vector-drawable-resources). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/VectorDrawableFragment.kt).\
\
![](./gifs/vector_drawable.gif)
### 14. Coordinator layout
You can find documentation [here](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/CoordinatorLayoutFragment.kt).\
\
![](./gifs/coordinator.gif)
### 15. Coordinator layout with BottomSheetBehavior
You can find documentation [here](https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetBehavior). Example of usage [here](https://github.com/nomtek/android-animations/blob/master/app/src/main/java/com/nomtek/animations/demo/BottomSheetFragment.kt).\
\
![](./gifs/bottom_sheet.gif)