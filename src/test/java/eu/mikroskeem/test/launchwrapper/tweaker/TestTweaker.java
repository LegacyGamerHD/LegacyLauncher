/*
 * This file is part of project Orion, licensed under the MIT License (MIT).
 *
 * Copyright (c) Original contributors ("I don't care" license? See https://github.com/Mojang/LegacyLauncher/issues/1)
 * Copyright (c) 2017-2018 Mark Vainomaa <mikroskeem@mikroskeem.eu>
 * Copyright (c) Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package eu.mikroskeem.test.launchwrapper.tweaker;

import eu.mikroskeem.test.launchwrapper.TestTransformer;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.lwts.AbstractTestTweaker;

/**
 * @author Mark Vainomaa
 */
public final class TestTweaker extends AbstractTestTweaker {
    @Override
    public void injectIntoClassLoader(LaunchClassLoader loader) {
        super.injectIntoClassLoader(loader);

        // Don't try to mess with LWTS
        loader.getTransformerExclusions().add("org.spongepowered.lwts.");
        loader.getClassLoaderExclusions().add("org.spongepowered.lwts.");

        // Load libraries from system classloader
        loader.getClassLoaderExclusions().add("eu.mikroskeem.shuriken.");
        loader.getClassLoaderExclusions().add("org.objectweb.asm.");

        // Don't transform test class and transformer
        loader.getTransformerExclusions().add("eu.mikroskeem.test.launchwrapper.LegacyLauncherTest");
        loader.getTransformerExclusions().add("eu.mikroskeem.test.launchwrapper.TestTransformer");

        // Register transformers
        loader.registerTransformer(TestTransformer.class.getName());
    }
}
